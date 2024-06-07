package com.caresaas.caresaasmobiletask.core.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/**
 * A class to model different levels of shape values.
 *
 * @param small The smallest value of [CareSaasShape].
 * @param medium The mid-value of [CareSaasShape].
 * @param large The largest value of [CareSaasShape].
 * @param largeTopRounding Asymmetric large shape with only top rounding.
 */
@Immutable
data class CareSaasShape(
    val none: Shape = RoundedCornerShape(size = 0.dp),
    val small: Shape = RoundedCornerShape(size = 0.dp),
    val medium: Shape = RoundedCornerShape(size = 0.dp),
    val large: Shape = RoundedCornerShape(size = 0.dp),
    val largeTopRounding: Shape = RoundedCornerShape(size = 0.dp),
)

/**
 * A composition local for [CareSaasShape].
 */
val LocalCareSaasShapes = staticCompositionLocalOf { CareSaasShape() }