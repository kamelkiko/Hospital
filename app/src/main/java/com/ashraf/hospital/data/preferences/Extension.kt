package com.ashraf.hospital.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.first

suspend fun DataStore<Preferences>.get(): Preferences {
    return data.first()
}