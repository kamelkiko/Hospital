package com.ashraf.hospital.data.repository.dto

data class BaseResponse<T>(
    val data: T? = null,
    val message: String? = null,
    val status: Int? = null,
)