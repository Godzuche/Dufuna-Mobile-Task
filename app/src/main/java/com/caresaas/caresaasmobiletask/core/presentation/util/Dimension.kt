package com.caresaas.caresaasmobiletask.core.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * Prevent font scaling
 * */
val TextUnit.nonScaledSp
    @Composable get() = (this.value / LocalDensity.current.fontScale).sp