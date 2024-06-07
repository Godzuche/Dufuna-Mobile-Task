package com.caresaas.caresaasmobiletask.feature.home.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.caresaas.caresaasmobiletask.core.designsystem.icon.CareSaaSIcons
import com.caresaas.caresaasmobiletask.core.designsystem.theme.BlackText
import com.caresaas.caresaasmobiletask.core.designsystem.theme.GreyLight
import com.caresaas.caresaasmobiletask.core.designsystem.theme.LocalCareSaasShapes
import com.caresaas.caresaasmobiletask.core.presentation.util.shimmerEffect

@Composable
fun TaskShimmer(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(LocalCareSaasShapes.current.medium)
            .shimmerEffect(),
    )
}

@Composable
fun TaskItem(modifier: Modifier = Modifier) {
    Card(
        onClick = {},
        shape = LocalCareSaasShapes.current.medium,
        colors = CardDefaults.cardColors(
            containerColor = GreyLight,
            contentColor = BlackText,
        )
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp,
                ),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = "4 Medications to take",
                    color = BlackText,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = CareSaaSIcons.ArrowCircleUp),
                    contentDescription = null,
                    tint = BlackText,
                )
            }
            // User
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Icon(
                    painter = painterResource(CareSaaSIcons.User),
                    contentDescription = null,
                    tint = BlackText,
                )
                Text(
                    text = "James",
                    color = BlackText,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
            //
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Icon(
                    painter = painterResource(CareSaaSIcons.DoorOpen),
                    contentDescription = null,
                    tint = BlackText,
                )
                Spacer(modifier = Modifier.width(2.5.dp))
                Text(
                    text = "Rm 3A",
                    color = BlackText,
                    style = MaterialTheme.typography.titleSmall,
                )
                Spacer(modifier = Modifier.width(16.dp))
                //
                Icon(
                    painter = painterResource(CareSaaSIcons.Bed),
                    contentDescription = null,
                    tint = BlackText,
                )
                Spacer(modifier = Modifier.width(2.5.dp))
                Text(
                    text = "Bed 45",
                    color = BlackText,
                    style = MaterialTheme.typography.titleSmall,
                )
                Spacer(modifier = Modifier.weight(1f))
                //
                Icon(
                    painter = painterResource(CareSaaSIcons.Clock),
                    contentDescription = null,
                    tint = BlackText,
                )
                Spacer(modifier = Modifier.width(2.5.dp))
                Text(
                    text = "09:00 AM",
                    color = BlackText,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}