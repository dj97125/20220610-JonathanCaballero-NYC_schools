package com.example.nyc_schools_test.di

import android.content.Context
import androidx.room.Room
import com.example.nyc_schools_test.common.DATABASE_NAME
import com.example.nyc_schools_test.model.local.SchoolDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            SchoolDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideSchoolsDao(db: SchoolDatabase) = db.getSchoolsDao()

    @Provides
    @Singleton
    fun provideSatDao(db: SchoolDatabase) = db.getSatsDao()

}