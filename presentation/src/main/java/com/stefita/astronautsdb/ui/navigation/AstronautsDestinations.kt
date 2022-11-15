package com.stefita.astronautsdb.ui.navigation

interface AstronautsDestination{
    val route: String
}

object AstronautsList : AstronautsDestination {
    override val route = "astronautsList"
}

object AstronautDetails: AstronautsDestination {
    override val route = "astronautDetails"
}