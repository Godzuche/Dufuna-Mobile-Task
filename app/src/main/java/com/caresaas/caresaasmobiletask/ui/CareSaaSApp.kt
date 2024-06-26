package com.caresaas.caresaasmobiletask.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.caresaas.caresaasmobiletask.core.designsystem.component.BottomBar
import com.caresaas.caresaasmobiletask.core.designsystem.theme.LocalNavigationProvider
import com.caresaas.caresaasmobiletask.core.presentation.util.isTrue
import com.caresaas.caresaasmobiletask.core.presentation.util.shouldShowBottomBar
import com.caresaas.caresaasmobiletask.navigation.CareSaaSNavHost
import com.caresaas.caresaasmobiletask.navigation.Screen
import com.caresaas.caresaasmobiletask.navigation.bottomBarDestinations

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

    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenWidthPx = with(density) { screenWidthDp.roundToPx() }
    val oneQuarterWidthPx = screenWidthPx / 3
    val oneQuarterWidthDp = screenWidthDp / 3
    val indicatorWidth = 45.dp

    val offset by animateIntOffsetAsState(
        targetValue = when (selectedTabIndex) {
            1 -> IntOffset(oneQuarterWidthPx * selectedTabIndex, 0)
            2 -> IntOffset(oneQuarterWidthPx * selectedTabIndex, 0)
            else -> IntOffset.Zero
        },
        label = "offset",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessLow,
            visibilityThreshold = IntOffset.VisibilityThreshold,
        )
    )

    Scaffold(
        modifier = Modifier,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            val visibility = currentDestination.shouldShowBottomBar()
            if (visibility.isTrue()) {
                Column {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = (oneQuarterWidthDp - indicatorWidth) / 2)
                            .offset { offset }
                            .background(
                                color = MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(2.dp),
                            )
                            .width(indicatorWidth)
                            .height(3.dp)
                    )

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
