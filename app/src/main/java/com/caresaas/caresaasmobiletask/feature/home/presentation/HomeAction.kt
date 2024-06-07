package com.caresaas.caresaasmobiletask.feature.home.presentation

sealed interface HomeAction {
    data object OnClockInClick: HomeAction
    data object OnClockOutClick: HomeAction
    data object OnTakeABreakClick: HomeAction
    data class OnTabClick(val tab: HomeTab): HomeAction
    data object OnRetryClick: HomeAction
}