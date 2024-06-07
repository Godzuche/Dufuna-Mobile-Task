package com.caresaas.caresaasmobiletask.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.caresaas.caresaasmobiletask.core.designsystem.component.BottomBar
import com.caresaas.caresaasmobiletask.core.designsystem.theme.LocalNavigationProvider
import com.caresaas.caresaasmobiletask.core.presentation.util.isTrue
import com.caresaas.caresaasmobiletask.core.presentation.util.shouldShowBottomBar
import com.caresaas.caresaasmobiletask.navigation.CareSaaSNavHost
import com.caresaas.caresaasmobiletask.navigation.Screen
import com.caresaas.caresaasmobiletask.navigation.bottomBarDestinations

enum class BottomTab {
    HOME,
    SEARCH,
    ACCOUNT,
}

@Composable
fun CareSaaSApp(
    startDestination: String,
) {
    val navController = LocalNavigationProvider.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination: NavDestination? = navBackStackEntry?.destination

    val selectedTabIndex: Int? = navBackStackEntry?.destination?.route?.let {
        when {
            it.contains(Screen.Home.route) -> 0
            it.contains(Screen.Search.route) -> 1
            it.contains(Screen.Account.route) -> 2
            else -> null
        }
    }

    Scaffold(
        modifier = Modifier,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            val visibility = currentDestination.shouldShowBottomBar()
            if (visibility.isTrue()) {
                BottomBar(
                    modifier = Modifier.fillMaxWidth(),
                    tabs = bottomBarDestinations,
                    currentDestination = currentDestination,
                    onItemClick = {
                        val navOptions = navOptions {
                            popUpTo(Screen.Home.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                        navController.navigate(it.screen.route, navOptions)
                    },
                )
            }
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                ),
        ) {
            CareSaaSNavHost(
                startDestination = startDestination,
            )
        }
    }
}
