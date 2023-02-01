package com.stefita.astronautsdb.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.stefita.astronautsdb.ui.astronautdetails.AstronautDetailsScreen
import com.stefita.astronautsdb.ui.astronautslist.AstronautsListScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AstronautsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = AstronautsList.route,
        modifier = modifier
    ) {

        composable(
            route = AstronautsList.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { 600 })
            },
            popEnterTransition = {
                slideInHorizontally(initialOffsetX = { 600 })
            }
        ) {
            AstronautsListScreen(
                onAstronautClicked = { astronautId ->
                    navController.navigateToAstronautDetails(
                        astronautId
                    )
                }
            )
        }

        composable(
            route = AstronautDetails.routeWithArgs,
            arguments = AstronautDetails.arguments,
            deepLinks = AstronautDetails.deepLinks,
        ) { navBackstackEntry ->

            val astronautId = navBackstackEntry.arguments?.getInt(AstronautDetails.astronautIdArg)
            astronautId?.let {
                AstronautDetailsScreen(astronautId = astronautId)
            }
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