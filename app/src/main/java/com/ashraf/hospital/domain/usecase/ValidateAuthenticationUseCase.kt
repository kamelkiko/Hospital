package com.ashraf.hospital.domain.usecase

import android.util.Patterns
import com.ashraf.hospital.domain.model.InvalidAddressException
import com.ashraf.hospital.domain.model.InvalidEmailException
import com.ashraf.hospital.domain.model.InvalidFirstNameException
import com.ashraf.hospital.domain.model.InvalidLastNameException
import com.ashraf.hospital.domain.model.InvalidPasswordException
import com.ashraf.hospital.domain.model.InvalidPhoneNumberException
import javax.inject.Inject

class ValidateAuthenticationUseCase @Inject constructor() {
    fun checkValidationLogin(email: String, password: String) {
        when {
            email.isBlank() || email.isEmpty() -> throw InvalidEmailException("Email can't be empty")
            password.isBlank() || password.isEmpty() ->
                throw InvalidPasswordException("Password can't be empty")
        }
    }

    fun checkValidationSignup(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        address: String,
        phoneNumber: String
    ) {
        when {
            firstName.isBlank() || firstName.isEmpty() -> throw InvalidFirstNameException("Name can't be empty")
            lastName.isBlank() || lastName.isEmpty() ->
                throw InvalidLastNameException("Name can't be empty")

            phoneNumber.isBlank() || phoneNumber.isEmpty() ->
                throw InvalidPhoneNumberException("Phone Number can't be empty")

            email.isBlank() || email.isEmpty() -> throw InvalidEmailException("Email can't be empty")

            address.isBlank() || address.isEmpty() -> throw InvalidAddressException("Address can't be empty")
            password.isBlank() || password.isEmpty() ->
                throw InvalidPasswordException("Password can't be empty")

            !(Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()) -> throw InvalidEmailException("This is not an email pattern")

            password.length < 6 ->
                throw InvalidPasswordException("Password should be more than 5 characters")
        }
    }
}