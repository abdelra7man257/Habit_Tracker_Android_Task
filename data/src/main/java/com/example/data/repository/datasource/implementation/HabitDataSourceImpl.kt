package com.example.data.repository.datasource.implementation

import com.example.data.mapper.HabitMapper
import com.example.data.repository.datasource.contracts.HabitDataSource
import com.example.data.room.HabitDao
import com.example.domain.model.Habit
import javax.inject.Inject

class HabitDataSourceImpl @Inject constructor(
    private val habitDao: HabitDao
) : HabitDataSource {
    override suspend fun insertHabit(habit: Habit): Long {
      return  habitDao.insertHabit(HabitMapper.domainToEntity(habit))
    }

    override suspend fun updateHabit(habit: Habit) {
        habitDao.updateHabit(HabitMapper.domainToEntity(habit))
    }

    override suspend fun deleteHabit(habit: Habit) {
        habitDao.deleteHabit(HabitMapper.domainToEntity(habit))
    }

    override suspend fun getHabits(): List<Habit> {
        return habitDao.getHabits().map {
            HabitMapper.entityToDomain(it)
        }
    }
}