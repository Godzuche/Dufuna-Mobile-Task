package com.caresaas.caresaasmobiletask.core.data.repository

import com.caresaas.caresaasmobiletask.core.data.remote.mapper.toTask
import com.caresaas.caresaasmobiletask.core.data.remote.model.TaskDto
import com.caresaas.caresaasmobiletask.core.data.remote.model.TasksResponse
import com.caresaas.caresaasmobiletask.core.di.CareSaaSDispatchers
import com.caresaas.caresaasmobiletask.core.di.Dispatcher
import com.caresaas.caresaasmobiletask.core.domain.model.Task
import com.caresaas.caresaasmobiletask.core.domain.repository.TasksRepository
import com.caresaas.caresaasmobiletask.core.domain.repository.UserPreferencesRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    client: HttpClient,
    private val userPreferencesRepository: UserPreferencesRepository,
    @Dispatcher(CareSaaSDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : BaseRepository(client), TasksRepository {

    override suspend fun getAllAssignedTasks(): List<Task> {
        val result = withContext(ioDispatcher) {
            val pref = async { userPreferencesRepository.userPreferences.first() }
            val user = pref.await().user
            val token = pref.await().token?.accessToken ?: ""
            val shortCode = user!!.organization

            val input = user.groups.first()
            val start = "Home-"
            val end = "-$shortCode"

            val startIndex = input.indexOf(start) + start.length
            val endIndex = input.indexOf(end)

            if (startIndex in 0..<endIndex) {
                val homeIdSubstring = input.substring(startIndex, endIndex)
                println("Home ID Substring: $homeIdSubstring")
            } else {
                println("No Home ID substring found in the input string.")
            }

            val careHomeId = input.substring(startIndex, endIndex)
            client.get {
                url {
                    appendPathSegments("tasks", shortCode, "careHome", careHomeId)
                    parameter("assignee", user.id)
                }
                contentType(ContentType.Application.Json)
                headers { append("Authorization", "Bearer $token") }
            }.body<TasksResponse>()
        }

        return result.data.map(TaskDto::toTask)
    }
}