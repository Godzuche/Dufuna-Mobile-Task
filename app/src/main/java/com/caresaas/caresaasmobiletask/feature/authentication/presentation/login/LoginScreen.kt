package com.caresaas.caresaasmobiletask.feature.authentication.presentation.login

import android.widget.Toast
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import com.caresaas.caresaasmobiletask.R
import com.caresaas.caresaasmobiletask.core.designsystem.component.CareSaasButton
import com.caresaas.caresaasmobiletask.core.designsystem.component.CareSaasLoadingScreen
import com.caresaas.caresaasmobiletask.core.designsystem.component.CareSaasTextField
import com.caresaas.caresaasmobiletask.core.designsystem.component.CareSaasToastMessage
import com.caresaas.caresaasmobiletask.core.designsystem.theme.BlackText
import com.caresaas.caresaasmobiletask.core.designsystem.theme.CareSaaSMobileTaskTheme
import com.caresaas.caresaasmobiletask.core.designsystem.theme.DisabledColor
import com.caresaas.caresaasmobiletask.core.designsystem.theme.GreyDark
import com.caresaas.caresaasmobiletask.core.designsystem.theme.RedMain
import com.caresaas.caresaasmobiletask.core.presentation.util.removeLayoutWidthConstraint
import com.caresaas.caresaasmobiletask.navigation.Screen

@Composable
fun LoginRoute(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoginScreen(
        state = state,
        onAction = viewModel::onAction,
    )

    LaunchedEffect(key1 = state.isLoginSuccess) {
        if (state.isLoginSuccess) {
            Toast.makeText(
                context,
                context.getString(R.string.login_successful),
                Toast.LENGTH_SHORT
            ).show()
            // Clear the entire backstack
            navController.navigateToHome()
        }
    }
}

@Composable
fun LoginScreen(
    state: LoginUiState,
    onAction: (LoginAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(all = 20.dp)
    ) {
        CareSaasLoadingScreen(modifier = Modifier.zIndex(4f), isLoading = state.isLoading)

        CareSaasToastMessage(
            isError = state.isError,
            isVisible = state.toastMessage != null,
            enterAnimation = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
            exitAnimation = slideOutVertically(targetOffsetY = { -it }) + fadeOut(),
            modifier = Modifier
                .zIndex(4f)
                .align(Alignment.TopCenter),
            content = { paddingValue ->
                val toastMessage = state.toastMessage ?: ""
                Text(
                    text = toastMessage,
                    style = MaterialTheme.typography.bodyLarge,
                    color = BlackText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValue)
                        .align(Alignment.Center),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                )
            }
        )

        Column {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(R.string.welcome_back),
                fontSize = 32.sp,
                style = MaterialTheme.typography.headlineMedium,
                color = BlackText,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(R.string.fill_your_details_to_get_started),
                style = MaterialTheme.typography.bodyLarge,
                color = GreyDark,
            )

            Spacer(modifier = Modifier.height(56.dp))

            CareSaasTextField(
                value = state.username,
                onValueChange = { onAction(LoginAction.OnUsernameChanged(it)) },
                label = stringResource(R.string.username),
                borderColor = state.userNameError?.let { MaterialTheme.colorScheme.errorContainer }
                    ?: DisabledColor,
            )
            state.userNameError?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            CareSaasTextField(
                value = state.password,
                onValueChange = { onAction(LoginAction.OnPasswordChanged(it)) },
                label = stringResource(R.string.password),
                borderColor = state.passwordError?.let { MaterialTheme.colorScheme.errorContainer }
                    ?: DisabledColor,
            )
            state.passwordError?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .removeLayoutWidthConstraint(20.dp)
                    .padding(end = 20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = state.isRememberMeChecked,
                    onCheckedChange = { onAction(LoginAction.OnRememberMeCheckedChange(it)) },
                    modifier = Modifier.padding(start = 8.dp),
                    colors = CheckboxDefaults.colors(
                        uncheckedColor = Color(0xFFDBDFEA),
                    )
                )
                Text(
                    text = stringResource(R.string.remember_me),
                    style = MaterialTheme.typography.bodyMedium,
                    color = GreyDark
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(R.string.forgot_password),
                    style = MaterialTheme.typography.bodyMedium,
                    color = RedMain,
                    modifier = Modifier
                        .clickable { onAction(LoginAction.OnForgotPasswordClick) },
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            CareSaasButton(
                title = stringResource(R.string.sign_in),
                onClick = { onAction(LoginAction.OnLoginClick) },
                enabled = state.isLoginButtonEnabled,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                style = MaterialTheme.typography.bodySmall,
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = GreyDark)) {
                        append(stringResource(R.string.don_t_have_an_account))
                    }
                    append(" ")
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(stringResource(R.string.contact_support))
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(),
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = GreyDark,
                text = buildAnnotatedString {
                    append(stringResource(R.string.by_clicking_sign_in_above_you_agree_to_arocare_s))
                    append(" ")
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(stringResource(R.string.terms_conditions))
                    }
                    append(" and ")
                    withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append(stringResource(R.string.privacy_policy))
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(),
            )

        }
    }
}

private fun NavController.navigateToHome() {
    val clearEntireBackStackNavOptions = navOptions {
        popUpTo(graph.startDestinationRoute!!) {
            inclusive = true
        }
    }
    navigate(Screen.Home.route, clearEntireBackStackNavOptions)
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun LoginPreview(modifier: Modifier = Modifier) {
    CareSaaSMobileTaskTheme {
        LoginScreen(
            state = LoginUiState(),
            onAction = {},
        )
    }
}