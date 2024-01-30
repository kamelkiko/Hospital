package com.ashraf.hospital.ui.screen.users

data class UsersUiState(
    val users: List<Users> = emptyList(),
)

data class Users(
    val name: String = "",
    val job: String = "",
    val id: Int = 0,
)
