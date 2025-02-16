package com.example.data.repository.datasource.contracts

import com.example.domain.model.Habit

interface HabitDataSource {
    suspend fun insertHabit(habit: Habit):Long
    suspend fun updateHabit(habit: Habit)
    suspend fun deleteHabit(habit: Habit)
    suspend fun getHabits():List<Habit>
}