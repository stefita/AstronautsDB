package com.stefita.astronautsdb.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
            AstronautsListScreen()
        }
    }
}