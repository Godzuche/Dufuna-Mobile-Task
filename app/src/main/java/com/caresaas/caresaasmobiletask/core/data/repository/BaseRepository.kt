package com.caresaas.caresaasmobiletask.core.data.repository

import com.caresaas.caresaasmobiletask.core.data.remote.model.BaseResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.statement.HttpResponse

abstract class BaseRepository(val client: HttpClient) {
    suspend inline fun <reified T> tryToExecute(method: HttpClient.() -> HttpResponse): T {
        try {
            return client.method().body()
        } catch (e: ClientRequestException) {
            val errorMessages = e.response.body<BaseResponse>()
            throw Throwable(errorMessages.message)
        }
    }
}