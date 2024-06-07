package com.caresaas.caresaasmobiletask.core.data.local.datastore

import androidx.datastore.core.DataStore
import com.caresaas.caresaasmobiletask.core.data.local.datastore.model.UserPreferences
import com.caresaas.caresaasmobiletask.core.domain.model.User
import com.caresaas.caresaasmobiletask.core.domain.model.UserData
import com.caresaas.caresaasmobiletask.core.domain.model.UserToken
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CareSaasPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>,
) {
    val userData = userPreferences.data
        .map {
            UserData(
                isUserAuthenticated = it.isUserAuthenticated,
                user = it.user,
                token = it.token,
                isSessionExpired = it.isSessionExpired,
            )
        }

    suspend fun setIsUserAuthenticated(isUserAuthenticated: Boolean) {
        userPreferences.updateData {
            it.copy(isUserAuthenticated = isUserAuthenticated)
        }
    }

    suspend fun setIsSessionExpired(isSessionExpired: Boolean) {
        userPreferences.updateData {
            it.copy(isSessionExpired = isSessionExpired)
        }
    }

    suspend fun setUser(user: User?) {
        userPreferences.updateData {
            it.copy(user = user)
        }
    }

    suspend fun saveToken(token: UserToken) {
        userPreferences.updateData {
            it.copy(token = token)
        }
    }

}