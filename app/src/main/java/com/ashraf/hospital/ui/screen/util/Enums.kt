package com.ashraf.hospital.ui.screen.util

enum class Gender(val type: String) {
    MALE("Male"), FEMALE("Female")
}

enum class Status(val type: String) {
    SINGLE("Single"), MARRIED("Married")
}

enum class Rule(val type: String) {
    DOCTOR("Doctor"),
    HR("Hr"),
    RECEPTIONIST("Receptionist"),
    MANAGER("Manger"),
    NURSE("Nurse")
}