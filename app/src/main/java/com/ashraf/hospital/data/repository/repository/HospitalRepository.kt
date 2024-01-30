package com.ashraf.hospital.data.repository.repository

import com.ashraf.hospital.data.repository.dto.CreateUserDto
import com.ashraf.hospital.data.repository.entity.ReportEntity
import com.ashraf.hospital.data.repository.mapper.toReport
import com.ashraf.hospital.data.repository.mapper.toUser
import com.ashraf.hospital.data.repository.mapper.toUserEntity
import com.ashraf.hospital.data.repository.source.LocalDataSource
import com.ashraf.hospital.data.repository.source.RemoteDataSource
import com.ashraf.hospital.data.repository.source.UserPreferencesDataSource
import com.ashraf.hospital.domain.model.Report
import com.ashraf.hospital.domain.model.User
import com.ashraf.hospital.domain.repository.IHospitalRepository
import javax.inject.Inject

class HospitalRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val userPreferencesDataSource: UserPreferencesDataSource
) : IHospitalRepository {
    override suspend fun login(email: String, password: String): User {
        return remoteDataSource.login(email, password).apply {
            userPreferencesDataSource.setIsLoggedIn(true)
            userPreferencesDataSource.setID(id!!)
            localDataSource.insertUser(this.toUserEntity())
        }.toUser()
    }

    override suspend fun register(user: CreateUserDto): User {
        return remoteDataSource.register(user).apply {
            userPreferencesDataSource.setIsLoggedIn(true)
            userPreferencesDataSource.setID(id!!)
            localDataSource.insertUser(this.toUserEntity())
        }.toUser()
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return userPreferencesDataSource.getIsLoggedIn()
    }

    override suspend fun getUserId(): Int {
        return userPreferencesDataSource.getID()
    }

    override suspend fun getUserById(id: Int): User {
        return localDataSource.getUserById(id).toUser()
    }

    override suspend fun createReport(name: String, description: String, date: String) {
        localDataSource.createReport(ReportEntity(0, name, description, date, isDone = false))
    }

    override suspend fun getAllReports(): List<Report> {
        return localDataSource.getAllReports().map {
            it.toReport()
        }
    }

    override suspend fun logout() {
        userPreferencesDataSource.setIsLoggedIn(false)
    }

    override suspend fun getAllUsers(): List<User> {
        return localDataSource.getAllUsers().map { it.toUser() }
    }

    override suspend fun createUser(user: CreateUserDto): User {
        return remoteDataSource.register(user).apply {
            localDataSource.insertUser(this.toUserEntity())
        }.toUser()
    }
}