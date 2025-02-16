package com.example.data.repository

import com.example.data.repository.datasource.contracts.HabitDataSource
import com.example.domain.model.Habit
import com.example.domain.repository.HabitRepository
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(
    private val habitDataSource: HabitDataSource
): HabitRepository {
    override suspend fun insertHabit(habit: Habit):Long {
      return  habitDataSource.insertHabit(habit)
    }

    override suspend fun updateHabit(habit: Habit) {
        habitDataSource.updateHabit(habit)
    }

    override suspend fun deleteHabit(habit: Habit) {
        habitDataSource.deleteHabit(habit)
    }

    override suspend fun getHabits(): List<Habit> {
       return habitDataSource.getHabits()
    }
}