package com.caresaas.caresaasmobiletask.feature.authentication.presentation.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.caresaas.caresaasmobiletask.core.domain.repository.AuthenticationRepository
import com.caresaas.caresaasmobiletask.core.domain.repository.UserPreferencesRepository
import com.caresaas.caresaasmobiletask.core.presentation.base.BaseViewModel
import com.caresaas.caresaasmobiletask.core.presentation.util.processNetworkError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthenticationRepository,
    private val userPreferencesRepository: UserPreferencesRepository,
) : BaseViewModel<LoginUiState>(LoginUiState()) {


    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnUsernameChanged -> {
                updateState { it.copy(username = action.username) }
                validateFields()
            }

            is LoginAction.OnPasswordChanged -> {
                updateState { it.copy(password = action.password) }
                validateFields()
            }

            is LoginAction.OnRememberMeCheckedChange -> {
                updateState { it.copy(isRememberMeChecked = action.isChecked) }
            }

            is LoginAction.OnForgotPasswordClick -> {
                println("Forgot Password Click")
            }

            is LoginAction.OnLoginClick -> {
                updateState {
                    it.copy(
                        isLoading = true,
                        toastMessage = null,
                        isError = false,
                    )
                }

                tryToExecute(
                    { authRepository.login(state.value.username, state.value.password) },
                    onSuccess = {
                        Log.d("Auth", "success: extract name: ${it.user.name}")
                        tryToExecute(
                            {
                                userPreferencesRepository.setIsUserAuthenticated(true)
                                userPreferencesRepository.setIsSessionExpired(false)
                                userPreferencesRepository.setUser(it.user)
                                userPreferencesRepository.saveToken(it.token)
                            },
                            onSuccess = {},
                            onError = {},
                        )
                        // Todo: Put in onSuccess and onError
                        updateState {
                            it.copy(
                                isLoading = false,
                                isLoginSuccess = true,
                                showDialog = true,
                                isError = false,
                                toastMessage = null,
                            )
                        }
                    },
                    onError = {
                        val message = it.processNetworkError()
                        Log.d("Auth", "error ${it.message}")
                        Log.d("Auth", "processed error $message")

                        viewModelScope.launch {
                            updateState {
                                it.copy(
                                    isLoading = false,
                                    isLoginSuccess = false,
                                    isError = true,
                                    toastMessage = "Login failed! :( \n$message",
                                )
                            }
                            delay(2000L)
                            updateState {
                                it.copy(
                                    isError = false,
                                    toastMessage = null,
                                )
                            }
                        }
                    }
                )
            }
        }
    }

    private fun validateFields() {
        state.value.username.run {
            if (isNotBlank() && isValidUsername().not()) {
                updateState {
                    it.copy(userNameError = "Username should be valid and at least 2 characters")
                }
            } else {
                updateState { it.copy(userNameError = null) }
            }
        }
        state.value.password.run {
            if (isNotBlank() && isValidPassword().not()) {
                updateState {
                    it.copy(
                        passwordError = "Password too weak, should be at least 8 characters," +
                                "contain at least an uppercase, lowercase letter, symbol and digit"
                    )
                }
            } else {
                updateState { it.copy(passwordError = null) }
            }
        }
    }
}

fun String.isValidPassword(): Boolean {
    val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$".toRegex()
    return passwordPattern.matches(this)
}

fun String.isValidUsername(): Boolean {
    val usernamePattern = "^\\S{2,}$".toRegex()
    return usernamePattern.matches(this)
}
