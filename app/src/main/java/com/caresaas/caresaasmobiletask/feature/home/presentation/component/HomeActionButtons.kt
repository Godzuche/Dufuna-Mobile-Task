package com.caresaas.caresaasmobiletask.feature.home.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.caresaas.caresaasmobiletask.R
import com.caresaas.caresaasmobiletask.core.designsystem.component.CareSaasButton
import com.caresaas.caresaasmobiletask.core.designsystem.theme.RedMain
import com.caresaas.caresaasmobiletask.core.designsystem.theme.YellowMain

@Composable
fun HomeActionButtons(
    isClockedIn: Boolean,
    onTakeABreakClick: () -> Unit,
    onClockOutClick: () -> Unit,
    onClockInClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (isClockedIn) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CareSaasButton(
                title = stringResource(R.string.take_a_break),
                onClick = onTakeABreakClick,
                colors = ButtonDefaults.buttonColors(containerColor = YellowMain),
                modifier = Modifier.weight(1f),
            )

            CareSaasButton(
                title = stringResource(R.string.clock_out),
                onClick = onClockOutClick,
                colors = ButtonDefaults.buttonColors(containerColor = RedMain),
                modifier = Modifier.weight(1f),
            )
        }
    } else {
        CareSaasButton(
            title = stringResource(R.string.clock_in),
            onClick = onClockInClick,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        )
    }
}