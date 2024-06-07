package com.caresaas.caresaasmobiletask.core.data.repository

import com.caresaas.caresaasmobiletask.core.data.local.datastore.CareSaasPreferencesDataSource
import com.caresaas.caresaasmobiletask.core.domain.model.UserData
import com.caresaas.caresaasmobiletask.core.di.CareSaaSDispatchers
import com.caresaas.caresaasmobiletask.core.di.Dispatcher
import com.caresaas.caresaasmobiletask.core.domain.model.User
import com.caresaas.caresaasmobiletask.core.domain.model.UserToken
import com.caresaas.caresaasmobiletask.core.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(
    private val preferencesDataSource: CareSaasPreferencesDataSource,
    @Dispatcher(CareSaaSDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : UserPreferencesRepository {

    override val userPreferences: Flow<UserData> =
        preferencesDataSource.userData.flowOn(ioDispatcher)

    override suspend fun setIsUserAuthenticated(isUserAuthenticated: Boolean) {
        withContext(ioDispatcher) {
            preferencesDataSource.setIsUserAuthenticated(isUserAuthenticated)
        }
    }

    override suspend fun setIsSessionExpired(isSessionExpired: Boolean) {
        withContext(ioDispatcher) {
            preferencesDataSource.setIsSessionExpired(isSessionExpired)
        }
    }

    override suspend fun setUser(user: User) {
        withContext(ioDispatcher) {
            preferencesDataSource.setUser(user)
        }
    }

    override suspend fun saveToken(token: UserToken) {
        withContext(ioDispatcher) {
            preferencesDataSource.saveToken(token)
        }
    }
}
