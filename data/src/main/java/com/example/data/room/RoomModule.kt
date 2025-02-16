package com.example.data.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideRoomDBInstance(@ApplicationContext context: Context): MyRoomDB
    {
       return Room.databaseBuilder(
            context = context,
            MyRoomDB::class.java,
            "db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideHabitDao(roomDB: MyRoomDB) = roomDB.habitDao()

    @Provides
    fun provideHabitProgressDao(roomDB: MyRoomDB) = roomDB.habitProgressDao()

}