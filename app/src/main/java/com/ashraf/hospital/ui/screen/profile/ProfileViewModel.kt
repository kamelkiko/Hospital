package com.ashraf.hospital.ui.screen.profile

import androidx.lifecycle.viewModelScope
import com.ashraf.hospital.domain.usecase.ManageAuthUseCase
import com.ashraf.hospital.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val auth: ManageAuthUseCase
) : BaseViewModel<ProfileUiState, ProfileUiEffect>(ProfileUiState()) {
    init {
        getUser()
    }

    fun logout() {
        viewModelScope.launch {
            auth.logout()
            sendNewEffect(ProfileUiEffect.NavigateToLoginScreen)
        }
    }

    private fun getUser() {
        tryToExecute(
            function = { auth.getUserId() },
            onSuccess = { id ->
                updateState { it.copy(id = id, isLoading = false) }
            },
            onError = { err ->
                updateState {
                    it.copy(
                        isLoading = false,
                        error = err,
                    )
                }
            },
        )
        tryToExecute(
            function = { auth.getUserById(state.value.id) },
            onSuccess = { user ->
                updateState {
                    it.copy(
                        id = user.id,
                        name = user.firstName + " " + user.lastName,
                        type = user.specialist,
                        email = user.email,
                        address = user.address,
                        status = user.status,
                        phone = user.mobile,
                        gender = user.gender,
                        birthdayDate = user.birthday,
                        isLoading = false
                    )
                }
            },
            onError = { err ->
                updateState {
                    it.copy(
                        isLoading = false,
                        error = err,
                    )
                }
            },
        )
    }
}