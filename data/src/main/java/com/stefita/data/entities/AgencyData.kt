package com.stefita.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agencies")
data class AgencyData(
    val abbrev: String,
    val administrator: String,
    val country_code: String,
    val description: String,
    val featured: Boolean,
    val founding_year: String,
    @PrimaryKey
    val id: Int,
    val image_url: String,
    val launchers: String,
    val logo_url: String,
    val name: String,
    val parent: Int?,
    val spacecraft: String,
    val type: String,
    val url: String
)