package com.caresaas.caresaasmobiletask.core.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserLoginDataDto(
    @SerialName("user")
    val user: UserDto,
    @SerialName("userToken")
    val token: UserTokenDto
)