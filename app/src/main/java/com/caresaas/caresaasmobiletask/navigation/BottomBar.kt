package com.caresaas.caresaasmobiletask.navigation

import com.caresaas.caresaasmobiletask.R
import com.caresaas.caresaasmobiletask.core.designsystem.icon.CareSaaSIcon
import com.caresaas.caresaasmobiletask.core.designsystem.icon.CareSaaSIcons

enum class BottomBarDestination(
    val selectedIcon: CareSaaSIcon,
    val unselectedIcon: CareSaaSIcon,
    val iconTextId: Int,
    val titleTextId: Int,
    val screen: Screen,
) {
    HOME(
        selectedIcon = CareSaaSIcon.DrawableResourceIcon(CareSaaSIcons.Home),
        unselectedIcon = CareSaaSIcon.DrawableResourceIcon(CareSaaSIcons.Home),
        iconTextId = R.string.home,
        titleTextId = R.string.home,
        screen = Screen.Home,
    ),
    SEARCH(
        selectedIcon = CareSaaSIcon.DrawableResourceIcon(CareSaaSIcons.Search),
        unselectedIcon = CareSaaSIcon.DrawableResourceIcon(CareSaaSIcons.Search),
        iconTextId = R.string.search,
        titleTextId = R.string.search,
        screen = Screen.Search,
    ),
    ACCOUNT(
        selectedIcon = CareSaaSIcon.DrawableResourceIcon(CareSaaSIcons.User),
        unselectedIcon = CareSaaSIcon.DrawableResourceIcon(CareSaaSIcons.User),
        iconTextId = R.string.account,
        titleTextId = R.string.account,
        screen = Screen.Account,
    ),
}

val bottomBarDestinations: List<BottomBarDestination> = BottomBarDestination.entries