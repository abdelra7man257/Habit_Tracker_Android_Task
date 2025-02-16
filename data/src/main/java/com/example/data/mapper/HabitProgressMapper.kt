package com.example.data.mapper

import com.example.data.model.HabitProgressEntity
import com.example.domain.model.HabitProgress

object HabitProgressMapper {
    fun domainToEntity(habitProgress: HabitProgress): HabitProgressEntity {
        return HabitProgressEntity(
            id = habitProgress.id,
            habitId = habitProgress.habitId,
            date = habitProgress.date,
            completionStatus = habitProgress.completionStatus
        )
    }
    fun entityToDomain(habitProgressEntity: HabitProgressEntity): HabitProgress {
        return HabitProgress(
            id = habitProgressEntity.id,
            habitId = habitProgressEntity.habitId,
            date = habitProgressEntity.date,
            completionStatus = habitProgressEntity.completionStatus
        )
    }


}