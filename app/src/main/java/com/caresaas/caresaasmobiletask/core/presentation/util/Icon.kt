package com.caresaas.caresaasmobiletask.core.presentation.util

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.caresaas.caresaasmobiletask.core.designsystem.icon.CareSaaSIcon

@Composable
fun CareSaaSIcon.DecodedCareSaaSIcon(
    modifier: Modifier = Modifier,
    tint: Color? = null,
    contentDescription: String? = null) =
    when (this) {
        is CareSaaSIcon.ImageVectorIcon -> {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription,
                modifier = modifier,
                tint = tint ?: Color.Unspecified,
            )
        }

        is CareSaaSIcon.DrawableResourceIcon -> {
            Icon(
                painter = painterResource(id = resourceId),
                contentDescription = contentDescription,
                modifier = modifier,
                tint = tint ?: Color.Unspecified,
            )
        }
    }