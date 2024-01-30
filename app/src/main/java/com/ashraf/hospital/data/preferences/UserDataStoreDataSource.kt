package com.ashraf.hospital.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.ashraf.hospital.data.repository.source.UserPreferencesDataSource
import javax.inject.Inject

class UserDataStoreDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : UserPreferencesDataSource {
    override suspend fun setIsLoggedIn(isLogged: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IsLoggedIn] = isLogged
        }
    }

    override suspend fun getIsLoggedIn(): Boolean {
        return dataStore.get()[PreferencesKeys.IsLoggedIn] ?: false
    }

    override suspend fun setID(id: Int) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.ID] = id
        }
    }

    override suspend fun getID(): Int {
        return dataStore.get()[PreferencesKeys.ID] ?: 0
    }
}