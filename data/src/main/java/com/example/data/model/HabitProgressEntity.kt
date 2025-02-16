package com.example.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "HabitProgressEntity"
    ,foreignKeys = [
        androidx.room.ForeignKey(
            entity = HabitEntity::class,
            parentColumns = ["id"],
            childColumns = ["habitId"],
            onDelete = androidx.room.ForeignKey.CASCADE
        )
    ]
    ,indices = [Index("habitId","date" , unique = true)]
)
data class HabitProgressEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val habitId: Long,
    val date: Long ,
    val completionStatus: Boolean = false
)