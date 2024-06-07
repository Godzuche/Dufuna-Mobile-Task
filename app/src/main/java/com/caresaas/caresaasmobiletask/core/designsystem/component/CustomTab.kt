package com.caresaas.caresaasmobiletask.core.designsystem.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.caresaas.caresaasmobiletask.core.designsystem.theme.DisabledColor

@Composable
fun CustomTab(
    title: String,
    onTabClicked: () -> Unit,
    modifier: Modifier = Modifier,
    isTabSelected: Boolean = false,
) {
    Tab(
        selected = isTabSelected,
        onClick = onTabClicked,
        selectedContentColor = MaterialTheme.colorScheme.background,
        unselectedContentColor = MaterialTheme.colorScheme.background,
        modifier = modifier,
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = if (isTabSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                DisabledColor
            },
            maxLines = 1,
        )
    }
}
