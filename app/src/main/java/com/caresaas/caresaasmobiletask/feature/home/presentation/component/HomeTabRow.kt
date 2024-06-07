package com.caresaas.caresaasmobiletask.feature.home.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.caresaas.caresaasmobiletask.R
import com.caresaas.caresaasmobiletask.core.designsystem.component.CustomTab
import com.caresaas.caresaasmobiletask.core.designsystem.theme.DividerColor
import com.caresaas.caresaasmobiletask.feature.home.presentation.HomeTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTabRow(
    selectedTab: HomeTab,
    onMedicationTabClick: () -> Unit,
    onActivitiesTabClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SecondaryTabRow(
        modifier = modifier,
        selectedTabIndex = selectedTab.ordinal,
        containerColor = Color.Transparent,
        indicator = {
            TabRowDefaults.SecondaryIndicator(
                Modifier
                    .tabIndicatorOffset(selectedTab.ordinal),
                height = 2.dp,
                color = MaterialTheme.colorScheme.primary,
            )
        },
        divider = {
            HorizontalDivider(
                color = DividerColor,
                thickness = 2.dp,
                modifier = Modifier.fillMaxWidth(),
            )
        },
    ) {
        CustomTab(
            title = stringResource(R.string.medication),
            isTabSelected = selectedTab == HomeTab.MEDICATION,
            onTabClicked = onMedicationTabClick,
        )

        CustomTab(
            title = stringResource(R.string.activities),
            isTabSelected = selectedTab == HomeTab.ACTIVITIES,
            onTabClicked = onActivitiesTabClick,
        )
    }
}