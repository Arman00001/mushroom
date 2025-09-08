package com.example.mushroom

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mushroom.view.route.HomePageRoute
import com.example.mushroom.view.route.MonitoringRoute
import com.example.mushroom.view.route.ShelfDetailsRoute

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.HomePage.path
    ) {
        composable(Route.HomePage.path) {
            HomePageRoute(
                onShelfClick = { id -> navController.navigate(Route.ShelfDetails.create(id)) },
                onMonitoringClick = { id -> navController.navigate(Route.Monitoring.create(id)) })
        }

        composable(
            route = Route.Monitoring.path,
            arguments = listOf(navArgument("shelfId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments!!.getInt("shelfId")
            MonitoringRoute(
                currentShelfId = id,
                onBack = { navController.navigateUp() }
            )
        }

        composable(
            route = Route.ShelfDetails.path,
            arguments = listOf(navArgument("shelfId") { type = NavType.IntType })
        ) { backStackEntry ->
            val shelfId = backStackEntry.arguments!!.getInt("shelfId")
            ShelfDetailsRoute(
                id = shelfId,
                onBack = { navController.navigateUp() },
                onMonitoringClick = { id -> navController.navigate(Route.Monitoring.create(id)) }
            )
        }
    }
}


sealed class Route(val path: String) {
    data object HomePage : Route("homepage")
    data object ShelfDetails : Route("shelfDetails/{shelfId}") {
        fun create(shelfId: Int) = "shelfDetails/$shelfId"
    }

    data object Monitoring : Route("monitoring/{shelfId}") {
        fun create(shelfId: Int) = "monitoring/$shelfId"
    }
}
