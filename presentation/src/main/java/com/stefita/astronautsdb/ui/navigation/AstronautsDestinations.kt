package com.stefita.astronautsdb.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

interface AstronautsDestination{
    val route: String
}

object AstronautsList : AstronautsDestination {
    override val route = "astronautsList"
}

object AstronautDetails: AstronautsDestination {
    override val route = "astronautDetails"

    const val astronautIdArg = "astronaut_id"
    val routeWithArgs = "$route/{$astronautIdArg}"
    val arguments = listOf(
        navArgument(astronautIdArg) { type = NavType.IntType }
    )
    val deepLinks = listOf(
        navDeepLink { uriPattern = "astronautsdb://$route/{$astronautIdArg}" }
    )
}