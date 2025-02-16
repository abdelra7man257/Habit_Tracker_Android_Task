package com.example.domain.repository

import com.example.domain.model.Habit


interface HabitRepository {
    suspend fun insertHabit(habit: Habit):Long
    suspend fun updateHabit(habit: Habit)
    suspend fun deleteHabit(habit: Habit)
    suspend fun getHabits():List<Habit>
}