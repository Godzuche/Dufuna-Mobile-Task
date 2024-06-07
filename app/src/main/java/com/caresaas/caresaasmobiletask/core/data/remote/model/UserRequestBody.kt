package com.caresaas.caresaasmobiletask.core.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRequestBody(
    @SerialName("userName")
    val userName: String,
    @SerialName("password")
    val password: String,
)