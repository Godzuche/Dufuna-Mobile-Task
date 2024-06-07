package com.caresaas.caresaasmobiletask.feature.home.presentation

import com.caresaas.caresaasmobiletask.core.domain.model.Task
import com.caresaas.caresaasmobiletask.core.domain.model.User

data class HomeUiState(
    val user: User? = null,
    val isClockedIn: Boolean = false,
    val selectedTab: HomeTab = HomeTab.MEDICATION,
    val tasksUiState: TasksUiState = TasksUiState.Loading,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSessionExpired: Boolean = false,
)

sealed interface TasksUiState{
    data class Success(val tasks: List<Task> = emptyList()): TasksUiState
    data object Loading: TasksUiState
    data class Error(val errorMessage: String? = null): TasksUiState
}
