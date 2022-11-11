package com.stefita.astronautsdb.entities

data class Agency(
    val abbrev: String,
    val administrator: String,
    val country_code: String,
    val description: String,
    val featured: Boolean,
    val founding_year: String,
    val id: Int,
    val image_url: String,
    val launchers: String,
    val logo_url: String,
    val name: String,
    val parent: Any,
    val spacecraft: String,
    val type: String,
    val url: String
)