package com.caresaas.caresaasmobiletask.core.domain.repository

import com.caresaas.caresaasmobiletask.core.domain.model.User
import com.caresaas.caresaasmobiletask.core.domain.model.UserLoginData

interface AuthenticationRepository {
    //region Auth
    suspend fun login(userName: String, password: String): UserLoginData
}