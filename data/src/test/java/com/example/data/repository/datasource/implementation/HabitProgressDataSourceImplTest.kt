package com.example.data.repository.datasource.implementation

import com.example.data.model.HabitProgressEntity
import com.example.data.room.HabitProgressDao
import com.example.domain.model.HabitProgress
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class HabitProgressDataSourceImplTest {

    private lateinit var habitProgressDataSource: HabitProgressDataSourceImpl
    @MockK
    private lateinit var habitProgressDao: HabitProgressDao
    @Before
    fun setUp() {
        habitProgressDao = mockk()
        habitProgressDataSource = HabitProgressDataSourceImpl(habitProgressDao)
    }
    @Test
    fun `when call getHabitProgressByDate then return HabitProgress List and call getHabitProgressByDate in HabitProgressDao` ()= runTest {

            val expectedId = 1L
            val expectedHabitId = 1L
            val expectedDate = 123456789L
            val expectedIsCompleted = true

            val mockkedHabitProgressEntity = HabitProgressEntity(expectedId , expectedHabitId , expectedDate ,expectedIsCompleted)

            val mockkedHabitProgress = HabitProgress(expectedId , expectedHabitId , expectedDate ,expectedIsCompleted)

            coEvery { habitProgressDao.getHabitProgressByDate(expectedDate) } returns listOf(mockkedHabitProgressEntity)

            val actualHabitProgressList = habitProgressDataSource.getHabitProgressByDate(expectedDate)
            coVerify(exactly = 1) { habitProgressDao.getHabitProgressByDate(expectedDate) }
            assertEquals(listOf(mockkedHabitProgress), actualHabitProgressList)


        }
}