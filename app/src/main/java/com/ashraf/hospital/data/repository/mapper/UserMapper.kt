package com.ashraf.hospital.data.repository.mapper

import com.ashraf.hospital.data.repository.dto.UserDto
import com.ashraf.hospital.data.repository.entity.UserEntity
import com.ashraf.hospital.domain.model.User

fun UserDto.toUser(): User = User(
    id = id ?: 0,
    accessToken = accessToken ?: "",
    address = address ?: "N/A",
    birthday = birthday ?: "N/A",
    email = email ?: "N/A",
    firstName = firstName ?: "N/A",
    gender = gender ?: "N/A",
    lastName = lastName ?: "N/A",
    mobile = mobile ?: "N/A",
    specialist = specialist ?: "N/A",
    status = status ?: "N/A",
    type = type ?: "N/A",
)

fun UserDto.toUserEntity(): UserEntity = UserEntity(
    id = id ?: 0,
    accessToken = accessToken ?: "",
    address = address ?: "N/A",
    birthday = birthday ?: "N/A",
    email = email ?: "N/A",
    firstName = firstName ?: "N/A",
    gender = gender ?: "N/A",
    lastName = lastName ?: "N/A",
    mobile = mobile ?: "N/A",
    specialist = specialist ?: "N/A",
    status = status ?: "N/A",
    type = type ?: "N/A",
    tokenType = tokenType ?: ""
)

fun UserEntity.toUser(): User = User(
    id = id,
    accessToken = accessToken,
    address = address,
    birthday = birthday,
    email = email,
    firstName = firstName,
    gender = gender,
    lastName = lastName,
    mobile = mobile,
    specialist = specialist,
    status = status,
    type = type,
)