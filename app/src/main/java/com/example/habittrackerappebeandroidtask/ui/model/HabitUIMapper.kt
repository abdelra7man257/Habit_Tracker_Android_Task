package com.example.habittrackerappebeandroidtask.ui.model

import com.example.domain.model.Habit
import com.example.domain.model.HabitProgress

object HabitUIMapper {
    fun toUIMapper(habit: Habit , habitProgress: HabitProgress):HabitUI
    {
        return HabitUI(
            progressId = habitProgress.id,
            habitId = habit.id,
            habitName = habit.habitName,
            habitDescription = habit.habitDescription,
            habitProgress = habitProgress.completionStatus,
            habitDate = habitProgress.date
        )
    }
}