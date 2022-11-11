package com.stefita.astronautsdb.entities

data class AstronautSource(
    val age: Int = 0,
    val agency: Agency? = null,
    val bio: String = "",
    val date_of_birth: String = "",
    val date_of_death: String? = null,
    val first_flight: String = "",
    val flights_count: Int = 0,
    val id: Int = 0,
    val instagram: String = "",
    val landings_count: Int = 0,
    val last_flight: String = "",
    val name: String = "",
    val nationality: String = "",
    val profile_image: String = "",
    val profile_image_thumbnail: String = "",
    val status: Status? = null,
    val twitter: String = "",
    val type: Type? = null,
    val url: String = "",
    val wiki: String = ""
)