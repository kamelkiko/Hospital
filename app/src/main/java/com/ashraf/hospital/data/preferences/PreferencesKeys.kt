package com.ashraf.hospital.data.preferences

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey

object PreferencesKeys {
    val IsLoggedIn = booleanPreferencesKey("is_logged_in")
    val ID = intPreferencesKey("id")
}