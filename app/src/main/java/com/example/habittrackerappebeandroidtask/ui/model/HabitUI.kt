package com.example.habittrackerappebeandroidtask.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class HabitUI(
    val progressId: Long = 0,
    val habitId: Long = 0,
    val habitName: String="",
    val habitDescription: String="",
    val habitProgress: Boolean = false,
    val habitDate: Long = 0
) : Parcelable
