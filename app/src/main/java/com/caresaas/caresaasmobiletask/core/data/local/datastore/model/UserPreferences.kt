package com.caresaas.caresaasmobiletask.core.data.local.datastore.model

import com.caresaas.caresaasmobiletask.core.domain.model.User
import com.caresaas.caresaasmobiletask.core.domain.model.UserToken
import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(
    val isUserAuthenticated: Boolean = false,
    val isSessionExpired: Boolean = false,
    val user: User? = null,
    val token: UserToken? = null,
)