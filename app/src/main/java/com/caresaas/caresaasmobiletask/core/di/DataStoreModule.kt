package com.caresaas.caresaasmobiletask.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import com.caresaas.caresaasmobiletask.core.data.local.datastore.UserPreferencesSerializer
import com.caresaas.caresaasmobiletask.core.data.local.datastore.model.UserPreferences
import com.caresaas.caresaasmobiletask.core.data.util.CryptoManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

private const val DATA_STORE_FILE_NAME = "user_preferences.json"

@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {

    @Provides
    @Singleton
    fun providesUserPreferenceDatastore(
        @ApplicationContext context: Context,
        @Dispatcher(CareSaaSDispatchers.IO) ioDispatcher: CoroutineDispatcher,
        userPreferencesSerializer: UserPreferencesSerializer
    ): DataStore<UserPreferences> =
        DataStoreFactory.create(
            serializer = userPreferencesSerializer,
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = {
                    // return default value
                    UserPreferences()
                }
            ),
            migrations = listOf(),
            scope = CoroutineScope(ioDispatcher + SupervisorJob()),
            produceFile = { context.dataStoreFile(DATA_STORE_FILE_NAME) }
        )

    @Singleton
    @Provides
    fun providesUserPreferencesSerializer(): UserPreferencesSerializer =
        UserPreferencesSerializer(/*cryptoManager = CryptoManager()*/)

}