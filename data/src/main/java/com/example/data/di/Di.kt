package com.example.data.di

import com.example.data.repository.HabitProgressRepositoryImpl
import com.example.data.repository.HabitRepositoryImpl
import com.example.data.repository.datasource.contracts.HabitDataSource
import com.example.data.repository.datasource.contracts.HabitProgressDataSource
import com.example.data.repository.datasource.implementation.HabitDataSourceImpl
import com.example.data.repository.datasource.implementation.HabitProgressDataSourceImpl
import com.example.domain.repository.HabitProgressRepository
import com.example.domain.repository.HabitRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface Di {

    @Binds
    fun bindHabitsDataSource(habitDataSource: HabitDataSourceImpl):HabitDataSource

    @Binds
    fun bindHabitRepository(habitRepositoryImpl: HabitRepositoryImpl):HabitRepository

    @Binds
    fun bindHabitProgressDataSource(habitProgressDataSourceImpl: HabitProgressDataSourceImpl): HabitProgressDataSource

    @Binds
    fun bindHabitProgressRepository(habitProgressRepository: HabitProgressRepositoryImpl):HabitProgressRepository
}