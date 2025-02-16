package com.example.data.repository

import com.example.data.repository.datasource.contracts.HabitProgressDataSource
import com.example.data.room.HabitProgressDao
import com.example.domain.model.HabitProgress
import com.example.domain.repository.HabitProgressRepository
import javax.inject.Inject

class HabitProgressRepositoryImpl @Inject constructor(
    private val habitProgressDataSource: HabitProgressDataSource
) :
    HabitProgressRepository {
    override suspend fun insertHabitProgress(habitProgress: HabitProgress) {
        habitProgressDataSource.insertHabitProgress(habitProgress)
    }
    override suspend fun getHabitProgress(habitId: Int, date: Long): HabitProgress {
       return habitProgressDataSource.getHabitProgress(habitId, date)
    }

    override suspend fun getHabitProgressByHabitId(habitId: Int): List<HabitProgress> {
       return habitProgressDataSource.getHabitProgressByHabitId(habitId)
    }

    override suspend fun getHabitProgressByDate(date: Long): List<HabitProgress> {
      return  habitProgressDataSource.getHabitProgressByDate(date)
    }

    override suspend fun getAllHabitProgress(): List<HabitProgress> {
        return habitProgressDataSource.getAllHabitProgress()
    }

    override suspend fun updateHabitProgress(habitProgress: HabitProgress) {
        habitProgressDataSource.updateHabitProgress(habitProgress)
    }
}