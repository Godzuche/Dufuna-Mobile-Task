package com.caresaas.caresaasmobiletask.core.di

import com.caresaas.caresaasmobiletask.core.data.repository.AuthenticationRepositoryImpl
import com.caresaas.caresaasmobiletask.core.data.repository.TasksRepositoryImpl
import com.caresaas.caresaasmobiletask.core.data.repository.UserPreferencesRepositoryImpl
import com.caresaas.caresaasmobiletask.core.domain.repository.AuthenticationRepository
import com.caresaas.caresaasmobiletask.core.domain.repository.TasksRepository
import com.caresaas.caresaasmobiletask.core.domain.repository.UserPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsAuthenticationRepository(
        authRepository: AuthenticationRepositoryImpl
    ): AuthenticationRepository

    @Binds
    fun bindsUserPreferencesRepository(
        userPreferencesRepository: UserPreferencesRepositoryImpl
    ): UserPreferencesRepository

    @Binds
    fun bindsTasksRepository(
        tasksRepository: TasksRepositoryImpl
    ): TasksRepository
}