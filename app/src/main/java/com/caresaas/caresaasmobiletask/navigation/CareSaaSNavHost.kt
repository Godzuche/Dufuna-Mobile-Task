package com.caresaas.caresaasmobiletask.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
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
    val context = LocalContext.current

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
            LoginRoute(
                navigateToHome = { navController.navigate(Screen.Home.route) }
            )
        }

        composable(Screen.Home.route) {
            // Clear the entire backstack
            val navOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
            }

            HomeRoute(
                navigateToNotification = {
                    Toast.makeText(context, "Notification action clicked", Toast.LENGTH_SHORT)
                        .show()
                },
                navigateToLogIn = { navController.navigate(Screen.Login.route, navOptions) }
            )
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