package com.ashraf.hospital.domain.repository

import com.ashraf.hospital.data.repository.dto.CreateUserDto
import com.ashraf.hospital.domain.model.Report
import com.ashraf.hospital.domain.model.User

interface IHospitalRepository {
    suspend fun login(
        email: String,
        password: String,
    ): User

    suspend fun register(
        user: CreateUserDto,
    ): User

    suspend fun isUserLoggedIn(): Boolean

    suspend fun getUserId(): Int

    suspend fun getUserById(id: Int): User

    suspend fun createReport(name: String, description: String, date: String)

    suspend fun getAllReports(): List<Report>
    suspend fun logout()
    suspend fun getAllUsers(): List<User>
    suspend fun createUser(user: CreateUserDto):User
}