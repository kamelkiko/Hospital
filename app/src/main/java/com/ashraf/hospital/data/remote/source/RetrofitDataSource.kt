package com.ashraf.hospital.data.remote.source

import com.ashraf.hospital.data.remote.service.HospitalApiService
import com.ashraf.hospital.data.repository.dto.BaseResponse
import com.ashraf.hospital.data.repository.dto.CreateUserDto
import com.ashraf.hospital.data.repository.dto.UserDto
import com.ashraf.hospital.data.repository.source.RemoteDataSource
import com.ashraf.hospital.domain.model.NoInternetConnectionException
import com.ashraf.hospital.domain.model.NullResultException
import com.ashraf.hospital.domain.model.UnAuthException
import com.ashraf.hospital.domain.model.UnKnownErrorException
import com.ashraf.hospital.domain.model.UserAlreadyExistException
import com.ashraf.hospital.domain.model.UserNotFoundException
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class RetrofitDataSource @Inject constructor(
    private val hospitalApiService: HospitalApiService
) : RemoteDataSource {
    override suspend fun login(email: String, password: String): UserDto {
        return wrapApiResponse { hospitalApiService.login(email, password) }.data
            ?: throw NullResultException("Empty data")
    }

    override suspend fun register(user: CreateUserDto): UserDto {
        return wrapApiResponse { hospitalApiService.register(user) }.data
            ?: throw NullResultException("Empty data")
    }


    //region wrap response
    private suspend fun <T> wrapApiResponse(
        request: suspend () -> Response<BaseResponse<T>>
    ): BaseResponse<T> {
        try {
            val response = request()
            if (response.isSuccessful && response.body()?.data != null) {
                return response.body() ?: throw NullResultException("No data")
            } else {
                throw when (response.body()?.message) {
                    "The selected email is invalid." -> UserNotFoundException(response.body()?.message)
                    "Unauthorized" -> UnAuthException("Invalid email or password")
                    "The email has already been taken." -> UserAlreadyExistException(response.body()?.message)
                    else -> UnKnownErrorException(response.body()?.message)
                }
            }
        } catch (e: UnknownHostException) {
            throw NoInternetConnectionException("No Internet")
        } catch (io: IOException) {
            throw NoInternetConnectionException(io.message)
        }
    }
    //endregion
}