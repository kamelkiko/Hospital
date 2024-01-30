package com.ashraf.hospital.ui.screen.welcome

import androidx.lifecycle.viewModelScope
import com.ashraf.hospital.domain.usecase.ManageAuthUseCase
import com.ashraf.hospital.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val auth: ManageAuthUseCase,
) : BaseViewModel<WelcomeUiState, Nothing>(WelcomeUiState()) {
    init {
        viewModelScope.launch {
            if (auth.isUserLoggedIn()) updateState { it.copy(isLoggedIn = true) }
            else updateState { it.copy(isLoggedIn = false) }
        }
    }
}