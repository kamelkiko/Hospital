package com.ashraf.hospital.data.repository.dto

import com.google.gson.annotations.SerializedName

data class CreateUserDto(
    val email: String? = null,
    val password: String? = null,
    @SerializedName("first_name")
    val firstName: String? = null,
    @SerializedName("last_name")
    val lastName: String? = null,
    val gender: String? = null,
    val specialist: String? = null,
    val status: String? = null,
    val birthday: String? = null,
    val mobile: String? = null,
    val address: String? = null,
    val type: String? = null,
)
