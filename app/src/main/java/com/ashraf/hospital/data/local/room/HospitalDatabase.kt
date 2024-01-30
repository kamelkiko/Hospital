package com.ashraf.hospital.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ashraf.hospital.data.repository.entity.ReportEntity
import com.ashraf.hospital.data.repository.entity.UserEntity

@Database(entities = [UserEntity::class, ReportEntity::class], version = 3, exportSchema = false)
abstract class HospitalDatabase : RoomDatabase() {
    abstract fun getHospitalDao(): HospitalDao
}