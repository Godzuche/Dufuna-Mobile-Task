package com.caresaas.caresaasmobiletask.core.designsystem.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.caresaas.caresaasmobiletask.core.designsystem.theme.BlackText
import com.caresaas.caresaasmobiletask.core.designsystem.theme.DisabledColor
import com.caresaas.caresaasmobiletask.core.designsystem.theme.GreyDark
import com.caresaas.caresaasmobiletask.core.designsystem.theme.LocalCareSaasShapes

@Composable
fun CareSaasTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    borderColor: Color = DisabledColor,
    singleLine: Boolean = true,
) {
    var isFieldFocused by remember {
        mutableStateOf(false)
    }

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                style = if (isFieldFocused) {
                    MaterialTheme.typography.labelSmall
                } else {
                    MaterialTheme.typography.bodySmall
                },
            )
        },
        singleLine = singleLine,
        shape = LocalCareSaasShapes.current.small,
        textStyle = MaterialTheme.typography.bodySmall,
        colors = TextFieldDefaults.colors(
            unfocusedLabelColor = DisabledColor,
            focusedLabelColor = GreyDark,
            focusedTextColor = BlackText,
            unfocusedTextColor = BlackText,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        modifier = modifier
            .onFocusChanged { isFieldFocused = it.isFocused }
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(4.dp),
            )
            .heightIn(min = 46.dp, max = with(LocalDensity.current) { 46.sp.toDp() })
            .fillMaxWidth(),
    )
}