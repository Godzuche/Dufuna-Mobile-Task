package com.caresaas.caresaasmobiletask.core.presentation.util

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

/**
 * A utility function that removes the width constraint due to the
 * content padding specified on the parent layout.
 * @param contentPadding is the amount of padding to remove.
 * */
fun Modifier.removeLayoutWidthConstraint(contentPadding: Dp) =
    this.layout { measurable, constraints ->
        val placeable: Placeable = measurable.measure(
            constraints.copy(
                maxWidth = constraints.maxWidth + (contentPadding * 2).roundToPx()
            )
        )
        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }

fun Modifier.notificationDot(): Modifier =
    composed {
        val tertiaryColor = Color.Red
        drawWithContent {
            drawContent()
            drawCircle(
                tertiaryColor,
                radius = 2.dp.toPx(),
                // This is based on the dimensions of the notification "bell icon"
                // With a width of 16dp and height of 16dp i.e radius = 8dp.
                center = center + Offset(
                    8.dp.toPx() * .45f + 4.dp.toPx(),
                    8.dp.toPx() * -.45f - 4.dp.toPx(),
                ),
            )
        }
    }

fun Modifier.shimmerEffect() = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -1 * size.width.toFloat(),
        targetValue = 1 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000
            )
        )
    )
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color.LightGray.copy(alpha = 0.6f),
                Color.LightGray.copy(alpha = 0.2f),
                Color.LightGray.copy(alpha = 0.6f)
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}