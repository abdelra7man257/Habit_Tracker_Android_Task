package com.example.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.HabitEntity

@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habitEntity: HabitEntity):Long

    @Delete
    suspend fun deleteHabit(habitEntity: HabitEntity)

    @Query("Select * from HabitEntity")
    suspend fun getHabits():List<HabitEntity>

    @Update
    suspend fun updateHabit(habitEntity: HabitEntity)
}