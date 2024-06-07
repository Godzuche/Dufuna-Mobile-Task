package com.caresaas.caresaasmobiletask.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.caresaas.caresaasmobiletask.R

val nunitoFontFamily = FontFamily(
    Font(resId = R.font.nunito_bold, weight = FontWeight.Bold),
    Font(resId = R.font.nunito_semibold, weight = FontWeight.SemiBold),
)

val robotoFontFamily = FontFamily(
    Font(resId = R.font.roboto, weight = FontWeight.Normal)
)

/**
 * CareSaaS typography.
 */
val CareSaaSTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 28.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 23.1.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 21.45.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = nunitoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 35.2.sp,
        letterSpacing = (-0.48).sp
    ),
    labelLarge = TextStyle(
        fontFamily = nunitoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.0026.sp
    ),
    labelSmall = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp,
        lineHeight = 20.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = nunitoFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 26.4.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = nunitoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 23.1.sp,
    )

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val appBarTextStyle = TextStyle(
    fontFamily = nunitoFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp,
    lineHeight = 18.5.sp,
    letterSpacing = (-0.0017).sp
)