package com.ashraf.hospital.data.local.source

import com.ashraf.hospital.data.local.room.HospitalDao
import com.ashraf.hospital.data.repository.entity.ReportEntity
import com.ashraf.hospital.data.repository.entity.UserEntity
import com.ashraf.hospital.data.repository.source.LocalDataSource
import javax.inject.Inject

class RoomDatabaseDataSource @Inject constructor(
    private val dao: HospitalDao,
) : LocalDataSource {
    override suspend fun insertUser(user: UserEntity) {
        dao.addUser(user)
    }

    override suspend fun getUserById(id: Int): UserEntity {
        return dao.getUser(id)
    }

    override suspend fun createReport(report: ReportEntity) {
        dao.createReport(report)
    }

    override suspend fun getAllReports(): List<ReportEntity> {
        return dao.getAllReports()
    }

    override suspend fun getAllUsers(): List<UserEntity> {
        return dao.getAllUsers()
    }
}