package com.ashraf.hospital.di

import android.content.Context
import androidx.room.Room
import com.ashraf.hospital.data.local.room.HospitalDao
import com.ashraf.hospital.data.local.room.HospitalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DATABASE_NAME = "hospital_database"

    @Singleton
    @Provides
    fun provideHospitalDatabase(
        @ApplicationContext context: Context
    ): HospitalDatabase {
        return Room.databaseBuilder(
            context,
            HospitalDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideHospitalDao(database: HospitalDatabase): HospitalDao = database.getHospitalDao()
}