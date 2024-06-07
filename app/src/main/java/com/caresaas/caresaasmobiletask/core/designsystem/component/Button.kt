package com.caresaas.caresaasmobiletask.core.designsystem.component

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.caresaas.caresaasmobiletask.core.designsystem.theme.CareSaasSize
import com.caresaas.caresaasmobiletask.core.designsystem.theme.DisabledColor
import com.caresaas.caresaasmobiletask.core.designsystem.theme.LocalCareSaasShapes

@Composable
fun CareSaasButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = LocalCareSaasShapes.current.small,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
//contentPadding = PaddingValues(horizontal = 8.dp, vertical = 18.dp),
) {
    Button(
        onClick = onClick,
        colors = colors.copy(
            disabledContainerColor = DisabledColor,
            disabledContentColor = Color.White,
            contentColor = Color.White,
        ),
        enabled = enabled,
        shape = shape,
        modifier = modifier
            .defaultMinSize(minHeight = 36.dp)
            .heightIn(
                min = CareSaasSize.buttonDefaultHeight.dp,
                max = with(LocalDensity.current) {
                    CareSaasSize.buttonDefaultHeight.sp.toDp()
                },
            ),
    ) {
        Text(title)
    }
}