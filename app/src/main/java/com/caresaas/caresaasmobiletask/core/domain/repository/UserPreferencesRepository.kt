package com.caresaas.caresaasmobiletask.core.domain.repository

import com.caresaas.caresaasmobiletask.core.domain.model.UserData
import com.caresaas.caresaasmobiletask.core.domain.model.User
import com.caresaas.caresaasmobiletask.core.domain.model.UserToken
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    val userPreferences: Flow<UserData>
    suspend fun setIsUserAuthenticated(isUserAuthenticated: Boolean)
    suspend fun setIsSessionExpired(isSessionExpired: Boolean)
    suspend fun setUser(user: User)
    suspend fun saveToken(token: UserToken)
}