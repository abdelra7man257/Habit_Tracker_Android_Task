package com.example.data.mapper

import com.example.data.model.HabitEntity
import com.example.domain.model.Habit

object HabitMapper {

    fun entityToDomain(habitEntity: HabitEntity):Habit
    {
        return Habit(
            id = habitEntity.id,
            habitName = habitEntity.habitName,
            habitDescription = habitEntity.habitDescription,
        )
    }

    fun domainToEntity(habit: Habit):HabitEntity
    {
        return HabitEntity(
            id = habit.id,
            habitName = habit.habitName,
            habitDescription = habit.habitDescription,
        )
    }
}