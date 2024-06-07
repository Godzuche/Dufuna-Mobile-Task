package com.caresaas.caresaasmobiletask.core.domain.model

data class UserLoginData(
    val user: User,
    val token: UserToken,
)