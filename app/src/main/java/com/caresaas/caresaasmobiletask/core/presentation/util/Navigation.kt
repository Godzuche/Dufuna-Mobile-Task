package com.caresaas.caresaasmobiletask.core.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.caresaas.caresaasmobiletask.navigation.BottomBarDestination
import com.caresaas.caresaasmobiletask.navigation.bottomBarDestinations

@Composable
fun NavHostController.shouldShowBottomBar(): Boolean {
    val navBackStackEntry by currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.let { route ->
        bottomBarDestinations.any {
            route.contains(it.name, true)
        }
    } ?: false
}

fun NavDestination?.isTopLevelDestinationInHierarchy(destination: BottomBarDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

fun NavDestination?.shouldShowBottomBar(): Boolean {
    return this?.route?.let { route ->
        route in bottomBarDestinations.map { it.screen.route }
    } ?: false
}
