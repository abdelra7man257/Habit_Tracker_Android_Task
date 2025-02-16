package com.example.data.repository.datasource.implementation

import com.example.data.mapper.HabitProgressMapper
import com.example.data.model.HabitProgressEntity
import com.example.data.repository.datasource.contracts.HabitProgressDataSource
import com.example.data.room.HabitProgressDao
import com.example.domain.model.HabitProgress
import javax.inject.Inject

class HabitProgressDataSourceImpl @Inject constructor(private val habitProgressDao: HabitProgressDao) :
    HabitProgressDataSource {
    override suspend fun insertHabitProgress(habitProgress: HabitProgress) {
        habitProgressDao.insertHabitProgress(HabitProgressMapper.domainToEntity(habitProgress))
    }

    override suspend fun getHabitProgress(habitId: Int, date: Long): HabitProgress {

        return HabitProgressMapper.entityToDomain(habitProgressDao.getHabitProgress(habitId, date)!!)
    }

    override suspend fun getHabitProgressByHabitId(habitId: Int): List<HabitProgress> {
     return habitProgressDao.getHabitProgressByHabitId(habitId).map {
            HabitProgressMapper.entityToDomain(it)
        }
    }

    override suspend fun getHabitProgressByDate(date: Long): List<HabitProgress> {
       return habitProgressDao.getHabitProgressByDate(date).map {
            HabitProgressMapper.entityToDomain(it)
        }

    }

    override suspend fun getAllHabitProgress(): List<HabitProgress> {
       return habitProgressDao.getAllHabitProgress().map {
            HabitProgressMapper.entityToDomain(it)
        }

    }

    override suspend fun updateHabitProgress(habitProgress: HabitProgress) {
        habitProgressDao.updateHabitProgress(HabitProgressMapper.domainToEntity(habitProgress))
    }
}