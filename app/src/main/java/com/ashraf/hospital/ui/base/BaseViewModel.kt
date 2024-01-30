package com.ashraf.hospital.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashraf.hospital.domain.model.EmptyInputException
import com.ashraf.hospital.domain.model.InvalidAddressException
import com.ashraf.hospital.domain.model.InvalidEmailException
import com.ashraf.hospital.domain.model.InvalidFirstNameException
import com.ashraf.hospital.domain.model.InvalidLastNameException
import com.ashraf.hospital.domain.model.InvalidPasswordException
import com.ashraf.hospital.domain.model.InvalidPhoneNumberException
import com.ashraf.hospital.domain.model.NoInternetConnectionException
import com.ashraf.hospital.domain.model.NotFoundException
import com.ashraf.hospital.domain.model.NullResultException
import com.ashraf.hospital.domain.model.ServerErrorException
import com.ashraf.hospital.domain.model.UnAuthException
import com.ashraf.hospital.domain.model.UnKnownErrorException
import com.ashraf.hospital.domain.model.UserAlreadyExistException
import com.ashraf.hospital.domain.model.UserNotFoundException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<S, E>(initialState: S) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<E?>()
    val effect = _effect.asSharedFlow()


    protected fun <T> tryToExecute(
        function: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (ErrorUiState) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
    ) {
        runWithErrorCheck(onError, dispatcher) {
            val result = function()
            onSuccess(result)
        }
    }


    protected fun <T> tryToCollect(
        function: suspend () -> Flow<T>,
        onNewValue: (T) -> Unit,
        onError: (ErrorUiState) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
    ) {
        runWithErrorCheck(onError, dispatcher) {
            function().collect {
                onNewValue(it)
            }
        }
    }


    protected fun updateState(updater: (S) -> S) {
        _state.update(updater)
    }


    protected fun sendNewEffect(newEffect: E) {
        viewModelScope.launch(Dispatchers.IO) {
            _effect.emit(newEffect)
        }
    }

    private fun runWithErrorCheck(
        onError: (ErrorUiState) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        function: suspend () -> Unit,
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                function()
            } catch (exception: NoInternetConnectionException) {
                onError(ErrorUiState.NoInternet(exception.message.toString()))
            } catch (exception: UserNotFoundException) {
                onError(ErrorUiState.UserNotFound(exception.message.toString()))
            } catch (exception: UserAlreadyExistException) {
                onError(ErrorUiState.UserAlreadyExist(exception.message.toString()))
            } catch (exception: UnAuthException) {
                onError(ErrorUiState.UnAuthorized(exception.message.toString()))
            } catch (exception: InvalidPasswordException) {
                onError(ErrorUiState.InvalidPassword(exception.message.toString()))
            } catch (exception: InvalidEmailException) {
                onError(ErrorUiState.InvalidEmail(exception.message.toString()))
            } catch (exception: InvalidFirstNameException) {
                onError(ErrorUiState.InvalidFirstName(exception.message.toString()))
            } catch (exception: InvalidLastNameException) {
                onError(ErrorUiState.InvalidLastName(exception.message.toString()))
            } catch (exception: InvalidAddressException) {
                onError(ErrorUiState.InvalidAddress(exception.message.toString()))
            } catch (exception: InvalidPhoneNumberException) {
                onError(ErrorUiState.InvalidPhoneNumber(exception.message.toString()))
            } catch (exception: EmptyInputException) {
                onError(ErrorUiState.EmptyInputData(exception.message.toString()))
            } catch (exception: ServerErrorException) {
                onError(ErrorUiState.ServerError(exception.message.toString()))
            } catch (exception: NotFoundException) {
                onError(ErrorUiState.NotFound(exception.message.toString()))
            } catch (exception: UnKnownErrorException) {
                onError(ErrorUiState.UnknownError(exception.message.toString()))
            } catch (exception: NullResultException) {
                onError(ErrorUiState.NoData(exception.message.toString()))
            } catch (exception: Exception) {
                onError(ErrorUiState.UnknownError(exception.message.toString()))
            }
        }
    }


    protected fun launchDelayed(duration: Long, block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(duration)
            block()
        }
    }
}