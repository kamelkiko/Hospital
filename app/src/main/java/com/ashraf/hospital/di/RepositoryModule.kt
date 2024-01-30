package com.ashraf.hospital.di

import com.ashraf.hospital.data.repository.repository.HospitalRepository
import com.ashraf.hospital.domain.repository.IHospitalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindAuthRepository(hospitalRepository: HospitalRepository): IHospitalRepository
}