package com.caresaas.caresaasmobiletask.core.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse(
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: String,
    @SerialName("code")
    val code: Int,
)