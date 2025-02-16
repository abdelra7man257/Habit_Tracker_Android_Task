package com.example.domain.usecase.habitusecase

import com.example.domain.model.Habit
import com.example.domain.repository.HabitRepository
import javax.inject.Inject

class InsertHabitUseCase @Inject constructor(private val habitRepository: HabitRepository) {
    suspend operator fun invoke(habit: Habit)=habitRepository.insertHabit(habit)
}