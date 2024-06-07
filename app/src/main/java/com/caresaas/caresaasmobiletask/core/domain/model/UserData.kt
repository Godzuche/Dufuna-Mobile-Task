package com.caresaas.caresaasmobiletask.core.domain.model

data class UserData(
    val isUserAuthenticated: Boolean,
    val isSessionExpired: Boolean,
    val user: User?,
    val token: UserToken?,
)
