package com.example.mushroom

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mushroom.view.route.HomePageRoute

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "homepage"
    ) {
        composable("homepage") {
            HomePageRoute(navController = navController)
        }
    }
}