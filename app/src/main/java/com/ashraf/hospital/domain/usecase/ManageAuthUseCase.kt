package com.ashraf.hospital.domain.usecase

import com.ashraf.hospital.data.repository.dto.CreateUserDto
import com.ashraf.hospital.domain.model.User
import com.ashraf.hospital.domain.repository.IHospitalRepository
import javax.inject.Inject

class ManageAuthUseCase @Inject constructor(
    private val hospitalRepository: IHospitalRepository,
    private val validate: ValidateAuthenticationUseCase,
) {
    suspend fun login(email: String, password: String): User {
        validate.checkValidationLogin(email, password)
        return hospitalRepository.login(email, password)
    }

    suspend fun register(user: CreateUserDto): User {
        validate.checkValidationSignup(
            email = user.email ?: "",
            password = user.password ?: "",
            firstName = user.firstName ?: "",
            lastName = user.lastName ?: "",
            address = user.address ?: "",
            phoneNumber = user.mobile ?: ""
        )
        return hospitalRepository.register(user)
    }

    suspend fun isUserLoggedIn() = hospitalRepository.isUserLoggedIn()

    suspend fun logout() = hospitalRepository.logout()

    suspend fun getUserId() = hospitalRepository.getUserId()

    suspend fun getUserById(id: Int) = hospitalRepository.getUserById(id)

    suspend fun getAllUsers() = hospitalRepository.getAllUsers()

    suspend fun createUser(user: CreateUserDto): User {
        validate.checkValidationSignup(
            email = user.email ?: "",
            password = user.password ?: "",
            firstName = user.firstName ?: "",
            lastName = user.lastName ?: "",
            address = user.address ?: "",
            phoneNumber = user.mobile ?: ""
        )
        return hospitalRepository.createUser(user)
    }
}