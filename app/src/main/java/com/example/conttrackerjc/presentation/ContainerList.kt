package com.example.conttrackerjc.presentation

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.conttrackerjc.R

@Composable
fun ContainerList(
    navController: NavHostController
) {
    val viewModel: ContainerListViewModel = viewModel()
    viewModel.getContainers()
    val state = viewModel.stateFlow.collectAsState().value

    val notificationPermissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted ->
                viewModel.switchNotificationPermission(isGranted)
            })
    Scaffold(topBar = {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = stringResource(R.string.name_version),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.tracked_containers_column_name)
                )
            }
        }
    }, bottomBar = {
        BottomAppBar(
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(R.string.developer_signature), fontSize = 12.sp)
                Text(text = stringResource(R.string.developer_email_contact), fontSize = 10.sp)
            }
        }
    }, floatingActionButton = {
        FloatingActionButton(modifier = Modifier.padding(bottom = 50.dp),

            onClick = {
                viewModel.switchDialog()
            }) {
            Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add button")
        }
        if (state.showDialog) {
            AddContainerDialog(contText = state.enterIDText,
                noteText = state.enterNoteText,
                onContValueChange = { viewModel.updateIdText(it) },
                onNoteValueChange = { viewModel.updateNoteText(it) },
                onConfirm = { viewModel.getContainer(it[0], it[1]) },
                onDismiss = { viewModel.switchDialog() })
        }
        if (state.showDeleteDialog) {
            DeleteContainerDialog(onYesClicked = { viewModel.deleteContainer(state.containerToBeDeleted) },
                onNoClicked = { viewModel.switchDeleteDialog() })
        }
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn {
                items(state.containerList) { container ->
                    ContainerItem(container = container,
                        onDeleteClicked = {
                            viewModel.switchDeleteDialog()
                            viewModel.assignToDelete(container)
                        },
                        onContainerClicked = {
                            navController.navigate("container/${container.containerId}")
                        },
                        onRefreshClicked = { viewModel.getAndUpdateContainer(container.containerId) },
                        onNotifyClicked = {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                            }
                            if (state.hasNotificationPermission) viewModel.updateNotification(
                                container.containerId, !container.notifyOn, container.uuid
                            )
                        })
                    Divider()
                }
            }

        }/*
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
            text = stringResource(R.string.tracked_containers_column_name)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn {
                items(state.containerList) { container ->
                    ContainerItem(
                        container = container,
                        onDeleteClicked = {
                            viewModel.switchDeleteDialog()
                            viewModel.assignToDelete(container)
                                          },
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
                    .padding(15.dp),

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
            if (state.showDeleteDialog){
                DeleteContainerDialog(onYesClicked = {viewModel.deleteContainer(state.containerToBeDeleted)}, onNoClicked = {viewModel.switchDeleteDialog()})
            }
        }
        BottomAppBar(
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(R.string.developer_signature), fontSize = 12.sp)
                Text(text = "lasmich@protonmail.com", fontSize = 10.sp)
            }
        }
    }*/
    }
}
