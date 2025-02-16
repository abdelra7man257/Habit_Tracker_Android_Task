package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "HabitEntity")
data class HabitEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Long=0,
    val habitName:String,
    val habitDescription:String,
)
