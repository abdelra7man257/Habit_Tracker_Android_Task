package com.example.habittrackerappebeandroidtask.ui.viewmodel

import com.example.domain.model.Habit
import com.example.domain.model.HabitProgress
import com.example.habittrackerappebeandroidtask.ui.model.HabitUI
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface SharedViewModelContract {

    interface ViewModel {
        val state:StateFlow<State>
        val event:SharedFlow<Events>
        fun sendAction(actions: Actions)
    }
    sealed class Actions {
        data object InsertHabit : Actions()
        data class ToggleTaskCompletion(val index:Int , val isCompleted:Boolean):Actions()
        data class GetHabits(val date: Long) : Actions()
        data class SetHabitName(val name:String="" , val description:String=""):Actions()
        data class SetHabitDescription(val description:String):Actions()
        data class SetSelectedHabit(val habit: HabitUI):Actions()
    }

    sealed class Events {
        data object ShowSuccess : Events()
        data object ShowError : Events()
        data object ShowLoading:Events()
    }

    data class State(
        val habits: List<Habit> = emptyList(),
        val habitProgress: MutableList<HabitProgress> = mutableListOf(),
        val listHabitUI: MutableList<HabitUI> = mutableListOf(),
        val isLoading: Boolean? = null,
        val error: String = "",
        val success: String="",
        val selectedDay:Long=0,
        val habitName:String="",
        val habitDescription:String="",
        val progress:Float=0.0f,
        val completedHabits: Int = 0,
        val totalHabits: Int = 0,
        val selectedHabit:HabitUI=HabitUI()
    )
}