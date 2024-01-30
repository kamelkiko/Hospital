package com.ashraf.hospital.ui.screen.users

import com.ashraf.hospital.domain.usecase.ManageAuthUseCase
import com.ashraf.hospital.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val users: ManageAuthUseCase,
) : BaseViewModel<UsersUiState, Nothing>(UsersUiState()) {
    init {
        getAllUsers()
    }

    fun getAllUsers() {
        tryToExecute(
            function = { users.getAllUsers() },
            onSuccess = { user ->
                updateState { usersUiState ->
                    usersUiState.copy(users = user.map {
                        Users(
                            name = it.firstName + " " + it.lastName,
                            job = it.specialist,
                            id = it.id,
                        )
                    })
                }
            },
            onError = {}
        )
    }
}