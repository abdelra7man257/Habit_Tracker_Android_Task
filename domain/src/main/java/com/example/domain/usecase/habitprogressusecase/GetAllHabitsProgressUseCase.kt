package com.example.domain.usecase.habitprogressusecase

import com.example.domain.model.HabitProgress
import com.example.domain.repository.HabitProgressRepository
import javax.inject.Inject

class GetAllHabitsProgressUseCase @Inject constructor(private val habitProgressRepository: HabitProgressRepository)  {

    suspend operator fun invoke(): List<HabitProgress> {
        return habitProgressRepository.getAllHabitProgress()
    }
}