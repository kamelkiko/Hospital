package com.ashraf.hospital.domain.model

data class User(
    val id: Int,
    val accessToken: String,
    val address: String,
    val birthday: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val lastName: String,
    val mobile: String,
    val specialist: String,
    val status: String,
    val type: String,
)
