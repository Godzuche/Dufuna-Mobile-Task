package com.caresaas.caresaasmobiletask.feature.authentication.presentation.login

sealed interface LoginAction{
    data class OnUsernameChanged(val username: String): LoginAction
    data class OnPasswordChanged(val password: String): LoginAction
    data class OnRememberMeCheckedChange(val isChecked: Boolean): LoginAction
    data object OnForgotPasswordClick: LoginAction
    data object OnLoginClick: LoginAction
}