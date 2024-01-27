package com.example.conttrackerjc.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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

@Composable
fun ContainerList(
    navController: NavHostController
) {
    val viewModel: ContainerListViewModel = viewModel()
    viewModel.getContainers()
    val state = viewModel.stateFlow.collectAsState().value
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
                    onRefreshClicked = {viewModel.getContainer(container.containerId)},
                    onNotifyClicked = {viewModel.updateNotification(container.containerId, !container.notifyOn)})
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
