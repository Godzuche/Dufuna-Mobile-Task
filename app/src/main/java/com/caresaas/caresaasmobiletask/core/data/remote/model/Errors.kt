package com.caresaas.caresaasmobiletask.core.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Errors(
    @SerialName("email")
    val email: List<String?>? = null
)