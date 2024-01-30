package com.ashraf.hospital.ui.screen.login

interface LoginInteractionListener {
    fun onClickLogin()
    fun onClickSignup()
    fun onEmailChanged(email: String)
    fun onPasswordChanged(password: String)
}