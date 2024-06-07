package com.caresaas.caresaasmobiletask.core.designsystem.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

private val DarkColorScheme = darkColorScheme(
    primary = BlueMain,
    surfaceVariant = Color.White,
    onSurfaceVariant = Grey,
    background = Color.White,
    surface = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = BlueMain
)

@Composable
fun CareSaaSMobileTaskTheme(
    darkTheme: Boolean = false,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (view.isInEditMode.not()) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    val defaultShapes = CareSaasShape(
        small = RoundedCornerShape(size = 4.dp),
        medium = RoundedCornerShape(size = 8.dp),
//        large = RoundedCornerShape(size = 10.dp),
//        largeTopRounding = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
    )

    CompositionLocalProvider(
        LocalNavigationProvider provides rememberNavController(),
        LocalCareSaasShapes provides defaultShapes
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = CareSaaSTypography,
            content = content
        )
    }
}

val LocalNavigationProvider = staticCompositionLocalOf<NavHostController> {
    error("No navigation host cont roller provided.")
}