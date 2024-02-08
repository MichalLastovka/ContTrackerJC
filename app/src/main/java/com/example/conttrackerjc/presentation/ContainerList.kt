package com.example.conttrackerjc.presentation

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContainerList(
    navController: NavHostController
) {
    val viewModel: ContainerListViewModel = viewModel()
    viewModel.getContainers()
    val state = viewModel.stateFlow.collectAsState().value

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            viewModel.switchNotificationPermission(isGranted)
        }
    )


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            title = { Text(text = "ContTracker") },
        )
        Text(
            text = "Sledované kontejnery"
        )
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn {
                items(state.containerList) { container ->
                    ContainerItem(
                        container = container,
                        onDeleteClicked = { viewModel.deleteContainer(container) },
                        onContainerClicked = {
                            navController.navigate("container/${container.containerId}")
                        },
                        onRefreshClicked = { viewModel.getAndUpdateContainer(container.containerId) },
                        onNotifyClicked = {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                            }
                            if (state.hasNotificationPermission)
                                viewModel.updateNotification(
                                    container.containerId,
                                    !container.notifyOn,
                                    container.uuid
                                )
                        }
                    )
                    Divider()
                }
            }

            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(20.dp),

                onClick = {
                    viewModel.switchDialog()
                }
            ) {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add button")
            }
            if (state.showDialog) {
                AddContainerDialog(
                    contText = state.enterIDText,
                    noteText = state.enterNoteText,
                    onContValueChange = { viewModel.updateIdText(it) },
                    onNoteValueChange = { viewModel.updateNoteText(it) },
                    onConfirm = { viewModel.getContainer(it[0], it[1]) },
                    onDismiss = { viewModel.switchDialog() })
            }
        }
    }
}
