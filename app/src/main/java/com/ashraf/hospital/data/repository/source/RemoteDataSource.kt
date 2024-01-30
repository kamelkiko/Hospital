package com.ashraf.hospital.data.repository.source

import com.ashraf.hospital.data.repository.dto.BaseResponse
import com.ashraf.hospital.data.repository.dto.CreateUserDto
import com.ashraf.hospital.data.repository.dto.UserDto

interface RemoteDataSource {
    suspend fun login(
        email: String,
        password: String,
    ): UserDto

    suspend fun register(
        user: CreateUserDto,
    ): UserDto
}