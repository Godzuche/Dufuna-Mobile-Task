package com.caresaas.caresaasmobiletask.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserToken(
    val accessToken: String,
    val refreshToken: String,
)