package com.caresaas.caresaasmobiletask.core.data.repository

import com.caresaas.caresaasmobiletask.core.data.remote.mapper.toUserEntity
import com.caresaas.caresaasmobiletask.core.data.remote.mapper.toUserToken
import com.caresaas.caresaasmobiletask.core.data.remote.model.UserRequestBody
import com.caresaas.caresaasmobiletask.core.data.remote.model.UserResponse
import com.caresaas.caresaasmobiletask.core.di.CareSaaSDispatchers
import com.caresaas.caresaasmobiletask.core.di.Dispatcher
import com.caresaas.caresaasmobiletask.core.domain.model.UserLoginData
import com.caresaas.caresaasmobiletask.core.domain.repository.AuthenticationRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    client: HttpClient,
    @Dispatcher(CareSaaSDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : BaseRepository(client = client), AuthenticationRepository {

    override suspend fun login(userName: String, password: String): UserLoginData {
        val result = withContext(ioDispatcher) {
            tryToExecute<UserResponse> {
                post {
                    url { appendPathSegments("auth", "login") }
                    contentType(ContentType.Application.Json)
                    setBody(UserRequestBody(userName, password))
                }
            }
        }

        println("Auth token: ${result.data.token}")

        return result.data.run {
            UserLoginData(
                user = user.toUserEntity(),
                token = token.toUserToken(),
            )
        }
    }
}