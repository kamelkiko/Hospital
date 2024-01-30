package com.ashraf.hospital.data.remote.service

import com.ashraf.hospital.data.repository.dto.BaseResponse
import com.ashraf.hospital.data.repository.dto.CreateUserDto
import com.ashraf.hospital.data.repository.dto.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface HospitalApiService {
    @POST("login")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String,
        @Query("device_token") deviceToken: String = "sdfsfsdfsdfsdfsf"
    ): Response<BaseResponse<UserDto>>

    @POST("register")
    suspend fun register(
        @Body user: CreateUserDto
    ): Response<BaseResponse<UserDto>>
}