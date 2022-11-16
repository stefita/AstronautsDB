package com.stefita.astronautsdb.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

interface AstronautsDestination{
    val route: String
    val screenTitle: String
}

object AstronautsList : AstronautsDestination {
    override val route = "astronautsList"
    override val screenTitle = "AstronautsDB"
}

object AstronautDetails: AstronautsDestination {
    override val route = "astronautDetails"
    override val screenTitle = "Details"

    const val astronautIdArg = "astronaut_id"
    val routeWithArgs = "$route/{$astronautIdArg}"
    val arguments = listOf(
        navArgument(astronautIdArg) { type = NavType.IntType }
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "astronautsdb://$route/{$astronautIdArg}" }
    )
}

val astronautsDbScreens = listOf(AstronautsList, AstronautDetails)