package com.caresaas.caresaasmobiletask.core.designsystem.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.caresaas.caresaasmobiletask.core.designsystem.theme.DisabledColor
import com.caresaas.caresaasmobiletask.core.designsystem.theme.Grey
import com.caresaas.caresaasmobiletask.core.designsystem.theme.ShadowColor
import com.caresaas.caresaasmobiletask.core.designsystem.theme.appBarTextStyle
import com.caresaas.caresaasmobiletask.core.presentation.util.DecodedCareSaaSIcon
import com.caresaas.caresaasmobiletask.core.presentation.util.isTopLevelDestinationInHierarchy
import com.caresaas.caresaasmobiletask.core.presentation.util.nonScaledSp
import com.caresaas.caresaasmobiletask.navigation.BottomBarDestination

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    tabs: List<BottomBarDestination>,
    currentDestination: NavDestination?,
    onItemClick: (BottomBarDestination) -> Unit,
) {
    NavigationBar(
        modifier = modifier.shadow(
            elevation = 4.dp,
            ambientColor = ShadowColor,
        ),
        tonalElevation = 0.dp,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.38f),
    ) {
        tabs.forEach { screen ->
            val selected: Boolean = currentDestination.isTopLevelDestinationInHierarchy(screen)

            NavigationBarItem(
                selected = selected,
                label = {
                    val textColor =
                        if (selected) MaterialTheme.colorScheme.primary else DisabledColor
                    Text(
                        text = stringResource(screen.titleTextId),
                        color = textColor,
                        maxLines = 1,
                        style = appBarTextStyle.copy(
                            fontSize = LocalTextStyle.current.fontSize.nonScaledSp,
                            lineHeight = LocalTextStyle.current.lineHeight.nonScaledSp,
                        ),
                    )
                },
                icon = {
                    val icon = if (selected) screen.selectedIcon else screen.unselectedIcon
                    val tint = if (selected) MaterialTheme.colorScheme.primary else Grey
                    icon.DecodedCareSaaSIcon(
                        contentDescription = stringResource(screen.iconTextId),
                        modifier = Modifier.size(24.dp),
                        tint = tint,
                    )
                },
                onClick = { onItemClick(screen) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Grey,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = Grey,
                    indicatorColor = Color.Transparent,
                ),
                alwaysShowLabel = true
            )
        }
    }
}

@Composable
fun CareSaaSNavigationSuiteScaffold(
    navigationSuiteItems: NiaNavigationSuiteScope.() -> Unit,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
    content: @Composable () -> Unit,
) {
    val layoutType = NavigationSuiteScaffoldDefaults
        .calculateFromAdaptiveInfo(windowAdaptiveInfo)
    val navigationSuiteItemColors = NavigationSuiteItemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            selectedIconColor = NiaNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = NiaNavigationDefaults.navigationContentColor(),
            selectedTextColor = NiaNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = NiaNavigationDefaults.navigationContentColor(),
            indicatorColor = NiaNavigationDefaults.navigationIndicatorColor(),
        ),
        navigationRailItemColors = NavigationRailItemDefaults.colors(
            selectedIconColor = NiaNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = NiaNavigationDefaults.navigationContentColor(),
            selectedTextColor = NiaNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = NiaNavigationDefaults.navigationContentColor(),
            indicatorColor = NiaNavigationDefaults.navigationIndicatorColor(),
        ),
        navigationDrawerItemColors = NavigationDrawerItemDefaults.colors(
            selectedIconColor = NiaNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = NiaNavigationDefaults.navigationContentColor(),
            selectedTextColor = NiaNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = NiaNavigationDefaults.navigationContentColor(),
        ),
    )

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            NiaNavigationSuiteScope(
                navigationSuiteScope = this,
                navigationSuiteItemColors = navigationSuiteItemColors,
            ).run(navigationSuiteItems)
        },
        layoutType = layoutType,
        containerColor = Color.Transparent,
        navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationBarContentColor = NiaNavigationDefaults.navigationContentColor(),
            navigationRailContainerColor = Color.Transparent,
        ),
        modifier = modifier,
    ) {
        content()
    }
}

/**
 * A wrapper around [NavigationSuiteScope] to declare navigation items.
 */
class NiaNavigationSuiteScope internal constructor(
    private val navigationSuiteScope: NavigationSuiteScope,
    private val navigationSuiteItemColors: NavigationSuiteItemColors,
) {
    fun item(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        icon: @Composable () -> Unit,
        selectedIcon: @Composable () -> Unit = icon,
        label: @Composable (() -> Unit)? = null,
    ) = navigationSuiteScope.item(
        selected = selected,
        onClick = onClick,
        icon = {
            if (selected) {
                selectedIcon()
            } else {
                icon()
            }
        },
        label = label,
        colors = navigationSuiteItemColors,
        modifier = modifier,
    )
}

/**
 * Now in Android navigation default values.
 */
object NiaNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}