package com.example.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.HabitEntity
import com.example.data.model.HabitProgressEntity


@Database(entities = [HabitEntity::class , HabitProgressEntity::class] , version = 3, exportSchema = false)
abstract class MyRoomDB:RoomDatabase() {
    abstract fun habitDao() : HabitDao
    abstract fun habitProgressDao() : HabitProgressDao
}