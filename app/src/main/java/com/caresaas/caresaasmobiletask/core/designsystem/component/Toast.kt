package com.caresaas.caresaasmobiletask.core.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.caresaas.caresaasmobiletask.core.designsystem.theme.DividerColor
import com.caresaas.caresaasmobiletask.core.designsystem.theme.LocalCareSaasShapes
import com.caresaas.caresaasmobiletask.core.designsystem.theme.ShadowColor

@Composable
fun CareSaasToastMessage(
    isError: Boolean = false,
    isVisible: Boolean = false,
    modifier: Modifier = Modifier,
    enterAnimation: EnterTransition = slideInVertically(initialOffsetY = { fullHeight -> fullHeight }) + fadeIn(),
    exitAnimation: ExitTransition = slideOutVertically(targetOffsetY = { fullHeight -> fullHeight }) + fadeOut(),
    content: @Composable (PaddingValues) -> Unit,
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = enterAnimation,
        exit = exitAnimation,
        modifier = modifier,
    ) {
        Surface(
            modifier = Modifier
                .widthIn(min = 216.dp)
                .padding(bottom = 30.dp)
                .shadow(
                    elevation = 5.dp,
                    shape = LocalCareSaasShapes.current.medium,
                    spotColor = ShadowColor
                ),
            color = MaterialTheme.colorScheme.background,
            border = BorderStroke(
                width = 0.5.dp,
                color = if (isError) MaterialTheme.colorScheme.errorContainer else DividerColor
            ),
            shape = LocalCareSaasShapes.current.medium,
            content = { content(PaddingValues(horizontal = 15.dp, vertical = 20.dp)) }
        )
    }
}