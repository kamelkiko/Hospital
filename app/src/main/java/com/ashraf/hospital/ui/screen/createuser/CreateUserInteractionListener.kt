package com.ashraf.hospital.ui.screen.createuser

interface CreateUserInteractionListener {
    fun onClickCreateUser()
    fun onEmailChanged(email: String)
    fun onPasswordChanged(password: String)
    fun onFirstNameChanged(name: String)
    fun onLastNameChanged(name: String)
    fun onAddressChanged(address: String)
    fun onPhoneNumberChanged(number: String)
    fun onGenderChanged(gender: String)
    fun onStatusChanged(status: String)
    fun onSpecialistChanged(specialist: String)
}