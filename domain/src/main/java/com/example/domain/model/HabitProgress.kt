package com.example.domain.model

data class HabitProgress(
    val id: Long = 0,
    val habitId: Long,
    val date: Long,
    val completionStatus: Boolean
)
