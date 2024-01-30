package com.ashraf.hospital.ui.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

private const val ERROR_MESSAGE = "Time must be bigger than zero"

fun <T> Flow<T>.throttleFirst(periodMillis: Long): Flow<T> {
    require(periodMillis > 0) { ERROR_MESSAGE }
    var lastTime = 0L
    return transform { value ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastTime >= periodMillis) {
            lastTime = currentTime
            emit(value)
        }
    }
}