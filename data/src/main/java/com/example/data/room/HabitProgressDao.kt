package com.example.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.data.model.HabitProgressEntity

@Dao
interface HabitProgressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabitProgress(habitProgress: HabitProgressEntity)

    @Query("SELECT * FROM HabitProgressEntity WHERE habitId = :habitId AND date = :date")
    suspend fun getHabitProgress(habitId: Int, date: Long): HabitProgressEntity?

    @Query("SELECT * FROM HabitProgressEntity WHERE habitId = :habitId")
    suspend fun getHabitProgressByHabitId(habitId: Int): List<HabitProgressEntity>

    @Query("SELECT * FROM HabitProgressEntity WHERE date = :date")
    suspend fun getHabitProgressByDate(date: Long): List<HabitProgressEntity>

    @Query("SELECT * FROM HabitProgressEntity")
    suspend fun getAllHabitProgress(): List<HabitProgressEntity>

    @Upsert
    suspend fun updateHabitProgress(habitProgress: HabitProgressEntity)


}