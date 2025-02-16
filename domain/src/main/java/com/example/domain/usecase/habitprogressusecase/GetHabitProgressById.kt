package com.example.domain.usecase.habitprogressusecase

import com.example.domain.repository.HabitProgressRepository
import javax.inject.Inject

class GetHabitProgressById @Inject constructor(private val habitProgressRepository: HabitProgressRepository)  {
    suspend operator fun invoke(habitId: Int) {
        habitProgressRepository.getHabitProgressByHabitId(habitId)
    }
}