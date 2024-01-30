package com.ashraf.hospital.data.repository.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("User")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val address: String,
    val birthday: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val mobile: String,
    val specialist: String,
    val status: String,
    val gender: String,
    val type: String,
    val accessToken: String,
    val tokenType: String,
)
