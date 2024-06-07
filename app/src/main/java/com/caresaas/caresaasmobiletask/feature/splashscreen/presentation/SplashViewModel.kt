package com.caresaas.caresaasmobiletask.feature.splashscreen.presentation

import com.caresaas.caresaasmobiletask.core.domain.repository.UserPreferencesRepository
import com.caresaas.caresaasmobiletask.core.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userDataRepository: UserPreferencesRepository,
) : BaseViewModel<SplashUiState>(SplashUiState()) {


    init {
        tryToCollect(
            function = { userDataRepository.userPreferences },
            onNewValue = { userData ->
                updateState {
                    it.copy(
                        isLoading = false,
                        error = null,
                        isSuccess = true,
                        data = userData,
                    )
                }
            },
            onError = { t ->
                updateState {
                    it.copy(
                        isLoading = false,
                        error = t.message,
                        isSuccess = false,
                        data = null,
                    )
                }
            }
        )
    }
}