package com.ashraf.hospital.di

import com.ashraf.hospital.data.local.source.RoomDatabaseDataSource
import com.ashraf.hospital.data.preferences.UserDataStoreDataSource
import com.ashraf.hospital.data.remote.source.RetrofitDataSource
import com.ashraf.hospital.data.repository.source.LocalDataSource
import com.ashraf.hospital.data.repository.source.RemoteDataSource
import com.ashraf.hospital.data.repository.source.UserPreferencesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindRemoteDataSource(retrofitDataSource: RetrofitDataSource): RemoteDataSource

    @Singleton
    @Binds
    abstract fun bindLocalDataSource(roomDatabaseDataSource: RoomDatabaseDataSource): LocalDataSource

    @Singleton
    @Binds
    abstract fun bindDataSourceDataSource(
        userDataStoreDataSource: UserDataStoreDataSource
    ): UserPreferencesDataSource
}