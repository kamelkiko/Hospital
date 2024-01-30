package com.ashraf.hospital.ui.screen.home

import com.ashraf.hospital.domain.usecase.ManageAuthUseCase
import com.ashraf.hospital.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val auth: ManageAuthUseCase
) : BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()), HomeInteractionListener {
    init {
        getUser()
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
                        type = user.type,
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

    override fun onClickProfile() {
        sendNewEffect(HomeUiEffect.NavigateToProfileScreen)
    }
}