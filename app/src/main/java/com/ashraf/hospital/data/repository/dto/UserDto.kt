package com.ashraf.hospital.data.repository.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("access_token")
    val accessToken: String? = null,
    val address: String? = null,
    val birthday: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    val email: String? = null,
    @SerializedName("first_name")
    val firstName: String? = null,
    val gender: String? = null,
    val id: Int? = null,
    @SerializedName("last_name")
    val lastName: String? = null,
    val mobile: String? = null,
    val specialist: String? = null,
    val status: String? = null,
    @SerializedName("token_type")
    val tokenType: String? = null,
    val type: String? = null,
    val verified: Boolean? = null,
)