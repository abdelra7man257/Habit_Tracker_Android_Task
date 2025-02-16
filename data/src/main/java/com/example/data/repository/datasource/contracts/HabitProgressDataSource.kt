package com.example.data.repository.datasource.contracts

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.HabitProgressEntity
import com.example.domain.model.HabitProgress

interface HabitProgressDataSource {

    suspend fun insertHabitProgress(habitProgress: HabitProgress)

    suspend fun getHabitProgress(habitId: Int, date: Long): HabitProgress

    suspend fun getHabitProgressByHabitId(habitId: Int): List<HabitProgress>

    suspend fun getHabitProgressByDate(date: Long): List<HabitProgress>

    suspend fun getAllHabitProgress(): List<HabitProgress>

    suspend fun updateHabitProgress(habitProgress: HabitProgress)
}