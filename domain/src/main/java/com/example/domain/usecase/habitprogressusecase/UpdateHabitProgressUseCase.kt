package com.example.domain.usecase.habitprogressusecase

import com.example.domain.model.HabitProgress
import com.example.domain.repository.HabitProgressRepository
import javax.inject.Inject

class UpdateHabitProgressUseCase @Inject constructor(private val habitProgressRepository: HabitProgressRepository)  {

    suspend operator fun invoke(habitProgress: HabitProgress) {
        habitProgressRepository.updateHabitProgress(habitProgress)

    }
}