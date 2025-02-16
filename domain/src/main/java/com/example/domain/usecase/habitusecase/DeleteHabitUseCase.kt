package com.example.domain.usecase.habitusecase

import com.example.domain.model.Habit
import com.example.domain.repository.HabitRepository
import javax.inject.Inject

class DeleteHabitUseCase @Inject constructor(private val habitRepository: HabitRepository) {

    suspend operator fun invoke(habit: Habit)=habitRepository.deleteHabit(habit)
}