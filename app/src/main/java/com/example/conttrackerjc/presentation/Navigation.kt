package com.example.conttrackerjc.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(

) {
    val viewModel: ContainerListViewModel = viewModel()
    val state = viewModel.stateFlow.collectAsState().value
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "container_list") {
        composable("container_list") {
            ContainerList(navController)
        }
        composable("container/{id}")
        {
            val id = it.arguments?.getString("id")
            viewModel.getContainerById(id!!)
            ContainerScreen(container = state.wantedCont)
        }
    }
}