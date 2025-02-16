package com.example.data.repository.datasource.implementation

import com.example.data.model.HabitEntity
import com.example.data.room.HabitDao
import com.example.domain.model.Habit
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class HabitDataSourceImplTest {

    private lateinit var habitDataSource: HabitDataSourceImpl
    @MockK
    private lateinit var habitDao: HabitDao

    @Before
    fun setUp() {
        habitDao = mockk()
        habitDataSource = HabitDataSourceImpl(habitDao)
    }

    @Test
    fun `when call insertHabit then return Habit Id and call insertHabit in HabitDao` () = runTest {
        val expectedId = 1L
        val mockkedHabitEntity = HabitEntity(expectedId , "habit name 1" ,"habit description 1")
        val mockkedHabit = Habit(expectedId , "habit name 1" ,"habit description 1")
        coEvery { habitDao.insertHabit(mockkedHabitEntity) } returns expectedId
        val actualId = habitDataSource.insertHabit(mockkedHabit)
        assertEquals(expectedId, actualId)
        coVerify(exactly = 1) { habitDao.insertHabit(mockkedHabitEntity) }
        assertEquals(actualId, expectedId)
    }
    @Test
    fun `when call getHabits then return Habit List and call getHabits in HabitDao` () = runTest {
        val expectedId = 1L
        val mockkedHabitEntity = HabitEntity(expectedId , "habit name 1" ,"habit description 1")
        val mockkedHabit = Habit(expectedId , "habit name 1" ,"habit description 1")
        coEvery { habitDao.getHabits() } returns listOf(mockkedHabitEntity)
        val actualHabitsList = habitDataSource.getHabits()
        assertEquals(listOf(mockkedHabit), actualHabitsList)
        coVerify(exactly = 1) { habitDao.getHabits() }
        assertEquals(listOf(mockkedHabit), actualHabitsList)

    }
}