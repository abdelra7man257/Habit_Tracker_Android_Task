package com.example.habittrackerappebeandroidtask.ui.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Habit
import com.example.domain.model.HabitProgress
import com.example.domain.usecase.habitprogressusecase.GetAllHabitsProgressUseCase
import com.example.domain.usecase.habitprogressusecase.GetHabitProgressByDate
import com.example.domain.usecase.habitprogressusecase.GetHabitProgressById
import com.example.domain.usecase.habitprogressusecase.InsertHabitProgressUseCase
import com.example.domain.usecase.habitprogressusecase.UpdateHabitProgressUseCase
import com.example.domain.usecase.habitusecase.DeleteHabitUseCase
import com.example.domain.usecase.habitusecase.GetHabitsAllUseCase
import com.example.domain.usecase.habitusecase.InsertHabitUseCase
import com.example.domain.usecase.habitusecase.UpdateHabitUseCase
import com.example.habittrackerappebeandroidtask.ui.model.HabitUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class SharedViewModel @Inject constructor(
    private val insertHabitUseCase: InsertHabitUseCase,
    private val updateHabitUseCase: UpdateHabitUseCase,
    private val deleteHabitUseCase: DeleteHabitUseCase,
    private val getHabitsAllUseCase: GetHabitsAllUseCase,
    private val getHabitsProgressByDayUseCase: GetHabitProgressByDate,
    private val insertHabitProgressUseCase: InsertHabitProgressUseCase,
    private val updateHabitProgressUseCase: UpdateHabitProgressUseCase
) :ViewModel(), SharedViewModelContract.ViewModel {
    private val _states = MutableStateFlow(SharedViewModelContract.State())
    private val _events = MutableSharedFlow<SharedViewModelContract.Events>()
    override val state: StateFlow<SharedViewModelContract.State>
        get() = _states.asStateFlow()
    override val event: SharedFlow<SharedViewModelContract.Events>
        get() = _events.asSharedFlow()

    init {
        getHabits(getCurrentDateMillis())
    }

    private fun getCurrentDateMillis(): Long {
        return LocalDate.now()
            .atStartOfDay()
            .toEpochSecond(ZoneId.systemDefault().rules.getOffset(Instant.now()))
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun sendAction(actions: SharedViewModelContract.Actions) {
        when(actions)
        {
            is SharedViewModelContract.Actions.GetHabits -> getHabits(actions.date)
            is SharedViewModelContract.Actions.InsertHabit -> insertHabit()
            is SharedViewModelContract.Actions.SetHabitName -> {
                _states.update {
                    it.copy(selectedHabit = it.selectedHabit.copy(habitName = actions.name))
                }
            }
            is SharedViewModelContract.Actions.SetHabitDescription -> {
                _states.update {
                    it.copy(selectedHabit = it.selectedHabit.copy(habitDescription = actions.description))
                }
            }
            is SharedViewModelContract.Actions.ToggleTaskCompletion-> toggleTaskCompletion(actions.index , actions.isCompleted)
            is SharedViewModelContract.Actions.SetSelectedHabit -> {
                _states.update {
                    it.copy(selectedHabit = actions.habit)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun toggleTaskCompletion(index:Int , isCompleted:Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                updateHabitProgressUseCase.invoke(HabitProgress(
                    id = _states.value.listHabitUI[index].progressId,
                    habitId = _states.value.listHabitUI[index].habitId,
                    date = getCurrentDateMillis(),
                    completionStatus = isCompleted
                ))
            }.onSuccess {
                _events.emit(SharedViewModelContract.Events.ShowSuccess)
                getHabits(getCurrentDateMillis())
            }.onFailure {
                _events.emit(SharedViewModelContract.Events.ShowError)

            }
        }
    }



    private fun insertHabit() {
        if (_states.value.selectedHabit.habitName.isEmpty() || _states.value.selectedHabit.habitDescription.isEmpty())
        {
            _events.tryEmit(SharedViewModelContract.Events.ShowError)
            _states.update {
                it.copy(error = "Please fill all fields")
            }
            return
        }
        val habit = Habit(id = _states.value.selectedHabit.habitId, habitName = _states.value.selectedHabit.habitName, habitDescription = _states.value.selectedHabit.habitDescription)
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                insertHabitUseCase.invoke(habit)
            }.onSuccess {
                insertHabitProgressUseCase.invoke(HabitProgress(
                    id = _states.value.selectedHabit.progressId,
                    habitId = it,
                    date = getCurrentDateMillis(),
                    completionStatus = false
                ))
                getHabits(getCurrentDateMillis())
                _states.update {state->
                    state.copy(selectedHabit = HabitUI())
                }
            }.onFailure {
                _events.emit(SharedViewModelContract.Events.ShowError)
            }

        }
    }

    private fun getHabits(date: Long) {
        val listOfHabitUI = mutableListOf<HabitUI>()
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _events.emit(SharedViewModelContract.Events.ShowLoading)
                val habits = async {  getHabitsAllUseCase.invoke() }.await().sortedBy { it.id }
                val habitProgress = async { getHabitsProgressByDayUseCase.invoke(date) }.await().sortedBy { it.habitId }
                habits.forEach { habit ->
                    val habitProg = habitProgress.find { it.habitId == habit.id }
                    listOfHabitUI.add(HabitUI(
                        progressId = habitProg?.id ?: 0,
                        habitId = habit.id,
                        habitName = habit.habitName,
                        habitDescription = habit.habitDescription,
                        habitProgress = habitProg?.completionStatus ?: false,
                        habitDate = habitProg?.date ?: 0
                    ))
                }
                val progress =  if (listOfHabitUI.isNotEmpty()) listOfHabitUI.filter { it.habitProgress }.size.toFloat() / listOfHabitUI.size.toFloat() else 0.0f
                _states.update {
                    it.copy(
                        progress = progress,
                        completedHabits = listOfHabitUI.filter { it.habitProgress }.size,
                        totalHabits = listOfHabitUI.size,
                    )
                }
                listOfHabitUI.sortedBy { it.habitId }

            }.onSuccess {list->
                _states.update {
                    it.copy(listHabitUI = list.toMutableList())
                }
            }.onFailure {
                _events.emit(SharedViewModelContract.Events.ShowError)
            }

        }
    }


}