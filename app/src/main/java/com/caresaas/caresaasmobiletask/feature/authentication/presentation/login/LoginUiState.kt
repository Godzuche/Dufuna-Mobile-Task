package com.caresaas.caresaasmobiletask.feature.authentication.presentation.login

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isRememberMeChecked: Boolean = false,
    val passwordError: String? = null,
    val userNameError: String? = null,

    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isLoginSuccess: Boolean = false,
    val toastMessage: String? = null,
    val shouldShowToast: Boolean = false,
    val showDialog: Boolean = false,
) {
    private val fieldsContainError: Boolean = userNameError != null || passwordError != null
    val isLoginButtonEnabled: Boolean =
        fieldsContainError.not() && username.isNotBlank() && password.isNotBlank()
}