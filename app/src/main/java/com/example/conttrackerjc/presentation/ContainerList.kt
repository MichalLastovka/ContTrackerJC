package com.example.conttrackerjc.presentation

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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.conttrackerjc.data.Container

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContainerList(
    navController: NavHostController
) {
    val viewModel: ContainerListViewModel = viewModel()
    viewModel.getContainers()
    val state = viewModel.stateFlow.collectAsState().value

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
            LazyColumn(){
                items(state.containerList){container->
                    ContainerItem(
                        container = container,
                        onDeleteClicked = {viewModel.deleteContainer(container)},
                        onContainerClicked = {
                            navController.navigate("container/${container.containerId}")
                        },
                        onRefreshClicked = {viewModel.getAndUpdateContainer(container.containerId)},
                        onNotifyClicked = {viewModel.updateNotification(container.containerId, !container.notifyOn)}
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
                    text = state.enterIDText,
                    onValueChange = {viewModel.updateIdText(it)},
                    onConfirm = {viewModel.getContainer(it)},
                    onDismiss = {viewModel.switchDialog()})
            }
        }
    }

}
