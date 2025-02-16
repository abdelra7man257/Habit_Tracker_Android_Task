package com.example.habittrackerappebeandroidtask.navigation

import HistoryScreenComposable
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.habittrackerappebeandroidtask.ui.model.HabitUI
import com.example.habittrackerappebeandroidtask.ui.screens.AddOrEditHabitScreen
import com.example.habittrackerappebeandroidtask.ui.screens.HabitsMainScreen
import com.example.habittrackerappebeandroidtask.ui.viewmodel.SharedViewModel
import com.example.habittrackerappebeandroidtask.ui.viewmodel.SharedViewModelContract
import com.google.gson.Gson
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: SharedViewModel,
    showSnackBar: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit)
    {
        viewModel.event.collect {
            when (it) {
                is SharedViewModelContract.Events.ShowSuccess -> {
                    Log.d("TAG", "NavGraph: Success")
                    showSnackBar(state.success)
                }
                is SharedViewModelContract.Events.ShowError -> {
                    Log.d("TAG", "NavGraph: Error")
                    showSnackBar(state.error)
                }
                is SharedViewModelContract.Events.ShowLoading -> {
                    Log.d("TAG", "NavGraph: Loading")
                }
            }

        }

    }

    NavHost(
        navController = navController,
        startDestination = Route.HabitListScreen,
        modifier = modifier
    ) {
        composable<Route.HabitListScreen> {
            HabitsMainScreen(
                modifier = Modifier,
                state = state,
                setSelectedHabit = {
                    viewModel.sendAction(SharedViewModelContract.Actions.SetSelectedHabit(it))
                },
                toggleHabitStatus = { index, isCompleted ->
                    viewModel.sendAction(
                        SharedViewModelContract.Actions.ToggleTaskCompletion(index, isCompleted)
                    )
                }
            ) {
                navController.navigate(it) {
                    popUpTo(Route.HabitListScreen) { inclusive = true }
                    launchSingleTop = true
                }
            }
        }

        composable<Route.AddEditScreen> {
            val args = it.toRoute<Route.AddEditScreen>()
            val habitUI = Gson().fromJson(args.habit, HabitUI::class.java)

            AddOrEditHabitScreen(
                modifier = modifier,
                onNameChange = { name ->
                    viewModel.sendAction(SharedViewModelContract.Actions.SetHabitName(name = name))
                },
                onCancel = {
                    navController.popBackStack()
                },
                onDescription = { description ->
                    viewModel.sendAction(
                        SharedViewModelContract.Actions.SetHabitDescription(description)
                    )
                },
                state = state,
            ) {
                viewModel.sendAction(SharedViewModelContract.Actions.InsertHabit)
                navController.navigate(Route.HabitListScreen) {
                    popUpTo(Route.HabitListScreen) { inclusive = true }
                    launchSingleTop = true
                }
            }
        }

        composable<Route.HabitHistoryScreen> {
            HistoryScreenComposable(habitsState = state) {
                viewModel.sendAction(SharedViewModelContract.Actions.GetHabits(it))
            }
        }
    }
}
