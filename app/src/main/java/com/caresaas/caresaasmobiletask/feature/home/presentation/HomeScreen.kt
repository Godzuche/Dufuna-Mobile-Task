package com.caresaas.caresaasmobiletask.feature.home.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.caresaas.caresaasmobiletask.R
import com.caresaas.caresaasmobiletask.core.designsystem.theme.CareSaaSMobileTaskTheme
import com.caresaas.caresaasmobiletask.core.presentation.util.shimmerEffect
import com.caresaas.caresaasmobiletask.feature.home.presentation.component.ActivitiesTabContent
import com.caresaas.caresaasmobiletask.feature.home.presentation.component.HomeActionButtons
import com.caresaas.caresaasmobiletask.feature.home.presentation.component.HomeTabRow
import com.caresaas.caresaasmobiletask.feature.home.presentation.component.HomeTopBar
import com.caresaas.caresaasmobiletask.feature.home.presentation.component.MedicationTabContent
import com.caresaas.caresaasmobiletask.navigation.Screen

@Composable
fun HomeRoute(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    HomeScreen(
        state = uiState,
        onAction = viewModel::onAction,
        navigateToNotification = {
            Toast.makeText(context, "Notification action clicked", Toast.LENGTH_SHORT)
                .show()
        },
        navigateToLogIn = { navController.navigateToLogin() },
    )
}

@Composable
fun HomeScreen(
    state: HomeUiState,
    onAction: (HomeAction) -> Unit,
    navigateToNotification: () -> Unit,
    navigateToLogIn: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val medicationListState = rememberLazyListState()
    val activitiesListState = rememberLazyListState()

    val topBarTitle = buildAnnotatedString {
        append(stringResource(R.string.hi))
        append(",")
        append(" ")
        append(state.user?.givenName ?: "")
        append("!")
        if (state.isClockedIn) {
            append(" ")
            withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                append(stringResource(R.string.clocked_in))
            }
        }
    }

    val topBarSubTitle = if (state.isClockedIn) {
        stringResource(id = R.string.you_can_now_begin_your_task)
    } else {
        stringResource(id = R.string.clock_in_to_begin_your_task)
    }

    Column(modifier = modifier) {
        if (state.user == null) {
            Box(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp)
                    .size(width = 190.dp, height = 52.dp)
                    .shimmerEffect()
            )
        } else {
            HomeTopBar(
                title = topBarTitle,
                subTitle = topBarSubTitle,
                onNotificationIconClick = navigateToNotification,
            )
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 20.dp),
        ) {
            HomeActionButtons(
                isClockedIn = state.isClockedIn,
                onTakeABreakClick = { onAction(HomeAction.OnTakeABreakClick) },
                onClockOutClick = { onAction(HomeAction.OnClockOutClick) },
                onClockInClick = { onAction(HomeAction.OnClockInClick) },
            )

            Spacer(modifier = Modifier.height(32.dp))

            HomeTabRow(
                selectedTab = state.selectedTab,
                onMedicationTabClick = { onAction(HomeAction.OnTabClick(HomeTab.MEDICATION)) },
                onActivitiesTabClick = { onAction(HomeAction.OnTabClick(HomeTab.ACTIVITIES)) },
            )

            when (state.selectedTab) {
                HomeTab.MEDICATION -> {
                    MedicationTabContent(
                        state = state.tasksUiState,
                        modifier = Modifier.weight(1f),
                        listState = medicationListState,
                        isSessionExpired = state.isSessionExpired,
                        onErrorButtonClick = {
                            if (state.isSessionExpired) {
                                navigateToLogIn()
                            } else {
                                onAction(HomeAction.OnRetryClick)
                            }
                        },
                    )
                }

                HomeTab.ACTIVITIES -> {
                    ActivitiesTabContent(
                        state = state.tasksUiState,
                        modifier = Modifier.weight(1f),
                        listState = activitiesListState,
                        isSessionExpired = state.isSessionExpired,
                        onErrorButtonClick = {
                            if (state.isSessionExpired) {
                                navigateToLogIn()
                            } else {
                                onAction(HomeAction.OnRetryClick)
                            }
                        },
                    )
                }
            }
        }

    }
}

private fun NavController.navigateToLogin() {
    // Clear the entire backstack
    val clearEntireBackStackNavOptions = navOptions {
        popUpTo(graph.startDestinationRoute!!) {
            inclusive = true
        }
    }
    navigate(
        Screen.Login.route,
        clearEntireBackStackNavOptions
    )
}

enum class HomeTab {
    MEDICATION,
    ACTIVITIES,
}


@Preview(showSystemUi = true)
@Composable
fun HomePreview(modifier: Modifier = Modifier) {
    CareSaaSMobileTaskTheme {
        HomeScreen(
            navigateToNotification = {},
            onAction = {},
            state = HomeUiState(),
            navigateToLogIn = {},
        )
    }
}