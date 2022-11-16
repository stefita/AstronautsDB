package com.stefita.astronautsdb.entities

data class Agency(
    val abbrev: String,
    val administrator: String,
    val countryCode: String,
    val description: String,
    val foundingYear: String,
    val id: Int,
    val imageUrl: String,
    val logoUrl: String,
    val name: String,
    val type: String,
    val url: String
)