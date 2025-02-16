package com.example.habittrackerappebeandroidtask.navigation

import com.example.domain.model.Habit
import com.example.habittrackerappebeandroidtask.ui.model.HabitUI
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data class AddEditScreen( val habit:String?=null) : Route

    @Serializable
    data object HabitListScreen : Route

    @Serializable
    data object HabitHistoryScreen : Route


}