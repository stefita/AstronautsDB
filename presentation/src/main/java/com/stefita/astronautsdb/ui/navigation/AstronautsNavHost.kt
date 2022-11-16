package com.stefita.astronautsdb.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.stefita.astronautsdb.ui.astronautdetails.AstronautDetailsScreen
import com.stefita.astronautsdb.ui.astronautslist.AstronautsListScreen

@Composable
fun AstronautsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AstronautsList.route,
        modifier = modifier
    ) {

        composable(route = AstronautsList.route) {
            AstronautsListScreen(
                onAstronautClicked = { astronautId ->
                    navController.navigateToAstronautDetails(
                        astronautId
                    )
                }
            )
        }

        composable(route = AstronautDetails.route) {
            AstronautDetailsScreen()
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }

private fun NavHostController.navigateToAstronautDetails(astronautId: Int) {
    this.navigateSingleTopTo("${AstronautDetails.route}/$astronautId")
}