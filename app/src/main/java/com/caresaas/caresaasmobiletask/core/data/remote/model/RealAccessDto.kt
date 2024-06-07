package com.caresaas.caresaasmobiletask.core.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RealAccessDto(
    @SerialName("roles")
    val roles: List<String>,
)