package com.ashraf.hospital.ui.screen.createuser

import com.ashraf.hospital.data.repository.dto.CreateUserDto
import com.ashraf.hospital.domain.usecase.ManageAuthUseCase
import com.ashraf.hospital.ui.base.BaseViewModel
import com.ashraf.hospital.ui.base.ErrorUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateUserViewModel @Inject constructor(
    private val manageAuth: ManageAuthUseCase,
) :
    BaseViewModel<CreateUserUiState, Nothing>(CreateUserUiState()),
    CreateUserInteractionListener {
    override fun onClickCreateUser() {
        updateState {
            it.copy(
                isLoading = true, error = null, errorMessage = ""
            )
        }
        createUser()
    }

    private fun createUser() {
        tryToExecute(
            function = {
                manageAuth.register(
                    CreateUserDto(
                        firstName = state.value.user.firstName,
                        lastName = state.value.user.lastName,
                        mobile = state.value.user.phoneNumber,
                        password = state.value.user.password,
                        email = state.value.user.email,
                        address = state.value.user.address,
                        birthday = "2001-08-08",
                        type = state.value.user.specialist,
                        specialist = state.value.user.specialist,
                        status = state.value.user.status,
                        gender = state.value.user.gender,
                    )
                )
            },
            onSuccess = {},
            onError = ::onFailed,
        )
    }

    private fun onFailed(errorUiState: ErrorUiState) {
        updateState {
            it.copy(
                isLoading = false, error = errorUiState
            )
        }
        handleError(errorUiState)
    }

    private fun handleError(error: ErrorUiState) {
        when (error) {
            is ErrorUiState.UnAuthorized -> {
                updateState {
                    it.copy(
                        errorMessage = error.errorMessage
                    )
                }
            }

            is ErrorUiState.UserAlreadyExist -> {
                updateState {
                    it.copy(
                        errorMessage = error.errorMessage
                    )
                }
            }

            is ErrorUiState.UserNotFound -> {
                updateState {
                    it.copy(
                        errorMessage = error.errorMessage
                    )
                }
            }

            is ErrorUiState.NoInternet -> {
                updateState {
                    it.copy(
                        errorMessage = error.errorMessage
                    )
                }
            }

            is ErrorUiState.InvalidEmail -> {
                updateState {
                    it.copy(
                        fieldsError = it.fieldsError.copy(emailError = error.errorMessage),
                    )
                }
            }

            is ErrorUiState.InvalidPassword -> {
                updateState {
                    it.copy(
                        fieldsError = it.fieldsError.copy(passwordError = error.errorMessage),
                    )
                }
            }

            is ErrorUiState.InvalidLastName -> {
                updateState {
                    it.copy(
                        fieldsError = it.fieldsError.copy(lastNameError = error.errorMessage),
                    )
                }
            }

            is ErrorUiState.InvalidFirstName -> {
                updateState {
                    it.copy(
                        fieldsError = it.fieldsError.copy(firstNameError = error.errorMessage),
                    )
                }
            }

            is ErrorUiState.InvalidAddress -> {
                updateState {
                    it.copy(
                        fieldsError = it.fieldsError.copy(addressError = error.errorMessage),
                    )
                }
            }

            is ErrorUiState.InvalidPhoneNumber -> {
                updateState {
                    it.copy(
                        fieldsError = it.fieldsError.copy(phoneNumberError = error.errorMessage),
                    )
                }
            }

            else -> {
                updateState {
                    it.copy(
                        errorMessage = "Something has been happened try again later.."
                    )
                }
            }
        }
    }

    override fun onEmailChanged(email: String) {
        updateState {
            it.copy(
                errorMessage = "",
                error = null,
                user = it.user.copy(
                    email = email,
                ),
                fieldsError = it.fieldsError.copy(
                    emailError = ""
                )
            )
        }
    }

    override fun onPasswordChanged(password: String) {
        updateState {
            it.copy(
                errorMessage = "",
                error = null,
                user = it.user.copy(
                    password = password,
                ),
                fieldsError = it.fieldsError.copy(
                    passwordError = ""
                )
            )
        }
    }

    override fun onFirstNameChanged(name: String) {
        updateState {
            it.copy(
                errorMessage = "",
                error = null,
                user = it.user.copy(
                    firstName = name,
                ),
                fieldsError = it.fieldsError.copy(
                    firstNameError = ""
                )
            )
        }
    }

    override fun onLastNameChanged(name: String) {
        updateState {
            it.copy(
                errorMessage = "",
                error = null,
                user = it.user.copy(
                    lastName = name,
                ),
                fieldsError = it.fieldsError.copy(
                    lastNameError = ""
                )
            )
        }
    }

    override fun onAddressChanged(address: String) {
        updateState {
            it.copy(
                errorMessage = "",
                error = null,
                user = it.user.copy(
                    address = address,
                ),
                fieldsError = it.fieldsError.copy(
                    addressError = ""
                )
            )
        }
    }

    override fun onPhoneNumberChanged(number: String) {
        updateState {
            it.copy(
                errorMessage = "",
                error = null,
                user = it.user.copy(
                    phoneNumber = number,
                ),
                fieldsError = it.fieldsError.copy(
                    phoneNumberError = ""
                )
            )
        }
    }

    override fun onGenderChanged(gender: String) {
        updateState {
            it.copy(
                errorMessage = "",
                error = null,
                user = it.user.copy(
                    gender = gender,
                ),
            )
        }
    }

    override fun onStatusChanged(status: String) {
        updateState {
            it.copy(
                errorMessage = "",
                error = null,
                user = it.user.copy(
                    status = status,
                ),
            )
        }
    }

    override fun onSpecialistChanged(specialist: String) {
        updateState {
            it.copy(
                errorMessage = "",
                error = null,
                user = it.user.copy(
                    specialist = specialist,
                ),
            )
        }
    }
}