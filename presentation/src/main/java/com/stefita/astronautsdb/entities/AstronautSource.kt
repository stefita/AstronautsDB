package com.stefita.astronautsdb.entities

data class AstronautSource(
    val age: Int = 0,
    val agency: Agency? = null,
    val bio: String? = "",
    val dateOfBirth: String? = "",
    val dateOfDeath: String? = null,
    val firstFlight: String? = null,
    val flightsCount: Int? = 0,
    val id: Int? = 0,
    val instagram: String? = "",
    val landingsCount: Int? = 0,
    val last_flight: String? = "",
    val name: String? = "",
    val nationality: String? = "",
    val profileImage: String? = "",
    val profileImageThumbnail: String? = "",
    val status: String? = null,
    val twitter: String? = "",
    val type: String? = null,
    val url: String? = "",
    val wiki: String? = null
)