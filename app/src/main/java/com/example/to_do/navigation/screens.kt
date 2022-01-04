package com.example.to_do.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.to_do.util.Actions
import com.example.to_do.util.Constant.LIST_SCREEN

class screens(navController: NavHostController) {
    val list:(Actions)->Unit={   action ->
        navController.navigate("list/${action.name}"){
            popUpTo(LIST_SCREEN){inclusive=true}
        }
    }
    val task:(Int)->Unit={ taskId->
        navController.navigate("task/$taskId")
    }
}