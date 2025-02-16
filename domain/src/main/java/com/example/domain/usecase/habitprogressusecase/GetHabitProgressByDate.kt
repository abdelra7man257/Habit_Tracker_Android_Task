package com.example.domain.usecase.habitprogressusecase

import com.example.domain.model.HabitProgress
import com.example.domain.repository.HabitProgressRepository
import javax.inject.Inject

class GetHabitProgressByDate @Inject constructor(private val habitProgressRepository: HabitProgressRepository)   {
    suspend operator fun invoke(date: Long): List<HabitProgress> {
      return  habitProgressRepository.getHabitProgressByDate(date)

    }
}