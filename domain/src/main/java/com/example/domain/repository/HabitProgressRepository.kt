package com.example.domain.repository

import com.example.domain.model.HabitProgress

interface HabitProgressRepository {

    suspend fun insertHabitProgress(habitProgress: HabitProgress)

    suspend fun getHabitProgress(habitId: Int, date: Long): HabitProgress
    suspend fun updateHabitProgress(habitProgress: HabitProgress)

    suspend fun getHabitProgressByHabitId(habitId: Int): List<HabitProgress>

    suspend fun getHabitProgressByDate(date: Long): List<HabitProgress>

    suspend fun getAllHabitProgress(): List<HabitProgress>

}