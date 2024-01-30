package com.ashraf.hospital.ui.screen.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ashraf.hospital.domain.model.User
import com.ashraf.hospital.domain.usecase.ManageAuthUseCase
import com.ashraf.hospital.ui.base.BaseViewModel
import com.ashraf.hospital.ui.base.ErrorUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val auth: ManageAuthUseCase,
) : BaseViewModel<LoginUiState, LoginUiEffect>(LoginUiState()), LoginInteractionListener {

    override fun onClickLogin() {
        login()
    }

    override fun onClickSignup() {
        sendNewEffect(LoginUiEffect.NavigateToRegisterScreen)
    }

    override fun onEmailChanged(email: String) {
        updateState {
            it.copy(
                email = email,
                error = null,
                errorMessage = "",
                emailError = "",
            )
        }
    }

    override fun onPasswordChanged(password: String) {
        updateState {
            it.copy(
                password = password,
                error = null,
                errorMessage = "",
                passwordError = "",
            )
        }
    }

    private fun login() {
        updateState {
            it.copy(
                isLoading = true, error = null, errorMessage = "", emailError = "",
                passwordError = "",
            )
        }
        tryToExecute(
            function = { auth.login(state.value.email, state.value.password) },
            onSuccess = ::onLoginSuccess,
            onError = ::onLoginFailed,
        )
    }

    private fun onLoginSuccess(user: User) {
        updateState {
            it.copy(
                isLoading = false, error = null, email = user.email, emailError = "",
                passwordError = "", errorMessage = "",
            )
        }
        sendNewEffect(LoginUiEffect.NavigateToHomeScreen)
    }

    private fun onLoginFailed(error: ErrorUiState) {
        updateState {
            it.copy(isLoading = false, error = error)
        }
        handleError(error)
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
                        emailError = error.errorMessage,
                    )
                }
            }

            is ErrorUiState.InvalidPassword -> {
                updateState {
                    it.copy(
                        passwordError = error.errorMessage,
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
}