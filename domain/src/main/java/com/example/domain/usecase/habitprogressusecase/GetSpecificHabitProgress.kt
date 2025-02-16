package com.example.domain.usecase.habitprogressusecase

import com.example.domain.repository.HabitProgressRepository
import javax.inject.Inject

class GetSpecificHabitProgress @Inject constructor(private val habitProgressRepository: HabitProgressRepository) {
    suspend operator fun invoke(habitId: Int, date: Long) {
        habitProgressRepository.getHabitProgress(habitId, date)
    }
}