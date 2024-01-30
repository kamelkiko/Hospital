package com.ashraf.hospital.domain.model

data class Report(
    val id: Int,
    val name: String,
    val description: String,
    val date: String,
    val isDone:Boolean,
)
