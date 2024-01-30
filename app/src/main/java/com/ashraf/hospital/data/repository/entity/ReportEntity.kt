package com.ashraf.hospital.data.repository.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Report")
data class ReportEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val description: String,
    val date: String,
    val isDone:Boolean,
)
