package com.caresaas.caresaasmobiletask.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
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
                Column {
                    selectedTabIndex?.let {
                        AnimateBox(
                            boxPositions = listOf(0, 1, 2),
                            currentBoxPosition = selectedTabIndex
                        )
                    }

                    BottomBar(
                        modifier = Modifier.fillMaxWidth(),
                        tabs = bottomBarDestinations,
                        currentDestination = currentDestination,
                        onItemClick = {
                            val navOptions = navOptions {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                            navController.navigate(it.screen.route, navOptions)
                        },
                    )
                }
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

@Composable
private fun AnimateBox(
    boxPositions: List<Int>,
    currentBoxPosition: Int,
) {
    val transition = updateTransition(
        currentBoxPosition,
        label = "Box animation"
    )

    val boxLeft by transition.animateDp(
        transitionSpec = {
            if (initialState < targetState) {
                // Box moves to the right.
                // Low stiffness spring for the left edge so it moves slower than the right edge.
                spring(stiffness = Spring.StiffnessVeryLow)
            } else {
                // Box moves to the left.
                // Medium stiffness spring for the left edge so it moves faster than the right edge.
                spring(stiffness = Spring.StiffnessMedium)
            }
        },
        label = "Box left"
    ) { boxPosition ->
        boxPositions[boxPosition] * 100.dp
    }

    val boxRight by transition.animateDp(
        transitionSpec = {
            if (initialState > targetState) {
                // Box moves to the left.
                // Low stiffness spring for the right edge so it moves slower than the left edge.
                spring(stiffness = Spring.StiffnessVeryLow)
            } else {
                // Box moves to the right
                // Medium stiffness spring for the right edge so it moves faster than the left edge.
                spring(stiffness = Spring.StiffnessMedium)
            }
        },
        label = "Box right"
    ) { boxPosition ->
        boxPositions[boxPosition] * 100.dp
    }

    Box(
        Modifier
//            .width(51.dp)
            .background(MaterialTheme.colorScheme.primary)
            .height(3.dp)
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(x = boxLeft)
            .width(boxRight - boxLeft)
            .padding(4.dp)
//            .fillMaxSize()
            .border(
                BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                RoundedCornerShape(4.dp)
            )
    )
}
