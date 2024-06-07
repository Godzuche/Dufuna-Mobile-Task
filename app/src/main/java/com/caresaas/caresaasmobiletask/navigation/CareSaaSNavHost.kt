package com.caresaas.caresaasmobiletask.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.caresaas.caresaasmobiletask.core.designsystem.theme.LocalNavigationProvider
import com.caresaas.caresaasmobiletask.feature.authentication.presentation.login.LoginRoute
import com.caresaas.caresaasmobiletask.feature.home.presentation.HomeRoute
import com.caresaas.caresaasmobiletask.feature.splashscreen.presentation.SplashRoute

@Composable
fun CareSaaSNavHost(
    startDestination: String,
    modifier: Modifier = Modifier,
    navController: NavHostController = LocalNavigationProvider.current,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable(Screen.Splash.route) {
            SplashRoute(
                navigateToLogin = { navController.navigate(Screen.Login.route) },
                navigateToHome = { navController.navigate(Screen.Home.route) },
            )
        }

        composable(Screen.Login.route) {
            LoginRoute(navController = navController)
        }

        composable(Screen.Home.route) {
            HomeRoute(navController = navController)
        }
        composable(Screen.Search.route) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "Search coming soon...")
            }
        }
        composable(Screen.Account.route) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "Account coming soon...")
            }
        }
    }
}