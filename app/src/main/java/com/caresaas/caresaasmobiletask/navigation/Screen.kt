package com.caresaas.caresaasmobiletask.navigation

sealed class Screen(val route: String, var arguments: Map<String, String>? = null) {
    data object Splash : Screen("splash")
    data object Login : Screen("login")
    data object Home : Screen("home")
    data object Search : Screen("search")
    data object Account : Screen("account")
}