package com.caresaas.caresaasmobiletask.feature.splashscreen.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.caresaas.caresaasmobiletask.core.designsystem.icon.CareSaaSIcons
import com.caresaas.caresaasmobiletask.core.designsystem.theme.CareSaaSMobileTaskTheme
import com.caresaas.caresaasmobiletask.core.presentation.util.isTrue

@Composable
fun SplashRoute(
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val isUserAuthenticated = uiState.data?.isUserAuthenticated

    SplashScreen(
        uiState = uiState,
        onTimeOut = {
            isUserAuthenticated?.let {
                if (it.isTrue()) {
                    navigateToHome()
                } else {
                    navigateToLogin()
                }
            }
        },
        modifier = modifier,
    )
}

@Composable
private fun SplashScreen(
    uiState: SplashUiState,
    onTimeOut: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(color = MaterialTheme.colorScheme.primary) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            val currentOnTimeOut by rememberUpdatedState(onTimeOut)

            LaunchedEffect(uiState) {
                if (uiState.isSuccess) {
                    currentOnTimeOut()
                }
            }

            Image(
                painter = painterResource(CareSaaSIcons.CareSaaSLogo),
                contentDescription = null,
            )
        }
    }
}

@Preview
@Composable
fun SplashPreview(modifier: Modifier = Modifier) {
    CareSaaSMobileTaskTheme {
        SplashScreen(
            uiState = SplashUiState(),
            onTimeOut = {},
        )
    }
}