package com.caresaas.caresaasmobiletask

import com.caresaas.caresaasmobiletask.core.domain.model.UserData
import com.caresaas.caresaasmobiletask.core.domain.repository.UserPreferencesRepository
import com.caresaas.caresaasmobiletask.core.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userDataRepository: UserPreferencesRepository,
) : BaseViewModel<MainActivityUiState>(MainActivityUiState.Loading) {


    init {
        tryToCollect(
            function = { userDataRepository.userPreferences },
            onNewValue = { userData ->
                updateState { MainActivityUiState.Success(userData) }
            },
            onError = { t -> }
        )
    }
}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(val data: UserData) : MainActivityUiState
}