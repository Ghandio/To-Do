package com.example.to_do.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_do.util.Actions
import com.example.to_do.util.Constant.TASK_ARGUMENT_KEY
import com.example.to_do.util.Constant.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    navigateToListScreen:(Actions)->Unit
){
    composable(
        route= TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY){
            type= NavType.IntType
        })
    ){

    }
}