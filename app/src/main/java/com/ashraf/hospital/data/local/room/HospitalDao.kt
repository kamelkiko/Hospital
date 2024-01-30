package com.ashraf.hospital.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ashraf.hospital.data.repository.entity.ReportEntity
import com.ashraf.hospital.data.repository.entity.UserEntity

@Dao
interface HospitalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: UserEntity)

    @Query("SELECT * FROM User WHERE id = :id")
    suspend fun getUser(id: Int): UserEntity

    @Query("SELECT * FROM User")
    suspend fun getAllUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createReport(report: ReportEntity)

    @Query("SELECT * FROM Report")
    suspend fun getAllReports(): List<ReportEntity>
}