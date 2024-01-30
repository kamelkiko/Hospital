package com.ashraf.hospital.data.repository.source

interface UserPreferencesDataSource {
    suspend fun setIsLoggedIn(isLogged: Boolean)
    suspend fun getIsLoggedIn(): Boolean

    suspend fun setID(id: Int)
    suspend fun getID(): Int
}