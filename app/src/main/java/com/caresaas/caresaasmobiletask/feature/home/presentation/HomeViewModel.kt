package com.caresaas.caresaasmobiletask.feature.home.presentation

import androidx.lifecycle.viewModelScope
import com.caresaas.caresaasmobiletask.core.data.remote.model.BaseResponse
import com.caresaas.caresaasmobiletask.core.domain.repository.TasksRepository
import com.caresaas.caresaasmobiletask.core.domain.repository.UserPreferencesRepository
import com.caresaas.caresaasmobiletask.core.presentation.base.BaseViewModel
import com.caresaas.caresaasmobiletask.core.presentation.util.processNetworkError
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val tasksRepository: TasksRepository,
) : BaseViewModel<HomeUiState>(HomeUiState()) {

    init {
        tryToCollect(
            { userPreferencesRepository.userPreferences },
            onNewValue = { userData ->
                updateState {
                    it.copy(
                        user = userData.user,
                        isSessionExpired = userData.isSessionExpired,
                    )
                }
            },
            onError = { println("Error reading preferences: ${it.message}") },
        )

        getAllAssignedTasks()
    }

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnClockInClick -> {
                updateState {
                    it.copy(isClockedIn = it.isClockedIn.not())
                }
            }

            is HomeAction.OnClockOutClick -> {
                updateState {
                    it.copy(isClockedIn = it.isClockedIn.not())
                }
            }

            is HomeAction.OnTakeABreakClick -> {
                println("Take a break clicked!")
            }

            is HomeAction.OnTabClick -> {
                updateState {
                    it.copy(selectedTab = action.tab)
                }
            }

            is HomeAction.OnRetryClick -> getAllAssignedTasks()
        }
    }

    private fun getAllAssignedTasks() {
        tryToExecute(
            {
                updateState { it.copy(tasksUiState = TasksUiState.Loading) }
                tasksRepository.getAllAssignedTasks()
            },
            onSuccess = { tasks ->
                updateState { it.copy(tasksUiState = TasksUiState.Success(tasks)) }
            },
            onError = { e ->
                viewModelScope.launch {
                    val message = when (e) {
                        is ClientRequestException -> {
                            val status = e.response.status
                            userPreferencesRepository.setIsSessionExpired(isSessionExpired = (status == HttpStatusCode.Unauthorized))
                            e.response.body<BaseResponse>().message
                        }

                        else -> e.processNetworkError()
                    }

                    println("Error: $message isSessionExpired: ${state.value.isSessionExpired}")
                    updateState {
                        it.copy(
                            tasksUiState = TasksUiState.Error(message)
                        )
                    }
                }
            }
        )

    }

}