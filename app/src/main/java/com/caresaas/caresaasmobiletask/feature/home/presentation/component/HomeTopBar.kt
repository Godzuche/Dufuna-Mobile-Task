package com.caresaas.caresaasmobiletask.feature.home.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.caresaas.caresaasmobiletask.core.designsystem.component.CareSaaSTopAppBar
import com.caresaas.caresaasmobiletask.core.designsystem.icon.CareSaaSIcons
import com.caresaas.caresaasmobiletask.core.designsystem.theme.BlackText
import com.caresaas.caresaasmobiletask.core.designsystem.theme.DisabledColor
import com.caresaas.caresaasmobiletask.core.designsystem.theme.GreyDark
import com.caresaas.caresaasmobiletask.core.designsystem.theme.LocalCareSaasShapes
import com.caresaas.caresaasmobiletask.core.designsystem.theme.appBarTextStyle
import com.caresaas.caresaasmobiletask.core.presentation.util.notificationDot

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    title: AnnotatedString,
    subTitle: String,
    onNotificationIconClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CareSaaSTopAppBar(
        title = {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    color = BlackText,
                    style = appBarTextStyle,
                )
                Text(
                    text = subTitle,
                    color = GreyDark,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        },
        actions = {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .size(32.dp)
                    .notificationDot()
                    .clip(LocalCareSaasShapes.current.medium)
                    .clickable { onNotificationIconClick() }
                    .border(
                        width = 1.dp,
                        color = DisabledColor,
                        shape = LocalCareSaasShapes.current.medium,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(CareSaaSIcons.Bell),
                    contentDescription = "Notification",
                )
            }
        },
    )
}