package com.caresaas.caresaasmobiletask.feature.home.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caresaas.caresaasmobiletask.R
import com.caresaas.caresaasmobiletask.core.designsystem.component.CareSaasButton
import com.caresaas.caresaasmobiletask.core.designsystem.theme.CareSaaSMobileTaskTheme
import com.caresaas.caresaasmobiletask.core.designsystem.theme.LocalCareSaasShapes
import com.caresaas.caresaasmobiletask.core.designsystem.theme.RedMain
import com.caresaas.caresaasmobiletask.core.presentation.util.shimmerEffect
import com.caresaas.caresaasmobiletask.feature.home.presentation.TasksUiState

@Composable
fun MedicationTabContent(
    isSessionExpired: Boolean,
    state: TasksUiState,
    listState: LazyListState,
    onErrorButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        state = listState,
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        when (state) {
            is TasksUiState.Success -> {
                if (state.tasks.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .height(300.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "You don't have any assigned tasks yet. :)\n" +
                                        "Please check back later.",
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                } else {
                    items(items = state.tasks) {
                        TaskItem()
                    }
                }
            }

            is TasksUiState.Loading -> {
                items(6) {
                    TaskShimmer()
                }
            }

            is TasksUiState.Error -> {
                item {
                    Box(
                        modifier = Modifier
                            .height(300.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            Text(
                                text = "Oops an error occurred! :(\n" +
                                        "${state.errorMessage}",
                                textAlign = TextAlign.Center,
                                color = RedMain,
                            )
                            CareSaasButton(
                                title = if (isSessionExpired) {
                                    stringResource(id = R.string.sign_in)
                                } else {
                                    stringResource(R.string.retry)
                                },
                                onClick = onErrorButtonClick,
                                modifier = Modifier.wrapContentWidth(),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MedicationTabContentPreview(modifier: Modifier = Modifier) {
    CareSaaSMobileTaskTheme {
        MedicationTabContent(
            state = TasksUiState.Error("Error occurred"),
            listState = rememberLazyListState(),
            onErrorButtonClick = {},
            isSessionExpired = false,
        )
    }
}