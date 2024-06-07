package com.caresaas.caresaasmobiletask.feature.splashscreen.presentation

import com.caresaas.caresaasmobiletask.core.domain.model.UserData

data class SplashUiState(
    val data: UserData? = null,
    val isLoading: Boolean = true,
    val isSuccess: Boolean = false,
    val error: String? = null
)
