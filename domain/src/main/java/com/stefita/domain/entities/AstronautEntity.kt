package com.stefita.domain.entities

data class AstronautEntity(
    val id: Int = 0,
    val age: Int = 0,
    val agency: AgencyEntity? = null,
    val bio: String = "",
    val dateOfBirth: String = "",
    val dateOfDeath: String? = null,
    val firstFlight: String? = null,
    val flights_count: Int = 0,
    val instagram: String = "",
    val landingsCount: Int = 0,
    val lastFlight: String? = null,
    val name: String = "",
    val nationality: String = "",
    val profileImage: String = "",
    val profileImageThumbnail: String = "",
    val status: String? = null,
    val twitter: String = "",
    val type: String? = null,
    val url: String = "",
    val wiki: String = ""
)
