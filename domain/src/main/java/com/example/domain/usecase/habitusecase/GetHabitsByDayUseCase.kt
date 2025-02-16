package com.example.domain.usecase.habitusecase

import com.example.domain.repository.HabitRepository
import javax.inject.Inject

class GetHabitsAllUseCase @Inject constructor(private val habitRepository: HabitRepository) {
    suspend operator fun invoke() = habitRepository.getHabits()
}