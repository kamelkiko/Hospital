package com.ashraf.hospital.data.repository.source

import com.ashraf.hospital.data.repository.entity.ReportEntity
import com.ashraf.hospital.data.repository.entity.UserEntity

interface LocalDataSource {
    suspend fun insertUser(user: UserEntity)

    suspend fun getUserById(id: Int): UserEntity

    suspend fun createReport(report: ReportEntity)

    suspend fun getAllReports(): List<ReportEntity>

    suspend fun getAllUsers(): List<UserEntity>
}