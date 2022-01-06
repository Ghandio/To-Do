package com.example.to_do.navigation.destinations

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_do.ui.task.TaskScreen
import com.example.to_do.ui.viewmodels.SharedViewModel
import com.example.to_do.util.Actions
import com.example.to_do.util.Constant.TASK_ARGUMENT_KEY
import com.example.to_do.util.Constant.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen:(Actions)->Unit
){
    composable(
        route= TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY){
            type= NavType.IntType
        })
    ){

            navBackStackEntry ->
             val taskId=navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
            sharedViewModel.getSelectedTaskFromDb(taskId)
        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        LaunchedEffect(selectedTask ){
            sharedViewModel.updateTaskFields(selectedTask = selectedTask)
        }

        TaskScreen(
            sharedViewModel=sharedViewModel,
            selectedTask=selectedTask ,
            navigateToListScreen = navigateToListScreen)


    }
}