package com.stefita.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StatusData(
    @PrimaryKey
    val id: Int = 0,
    val name: String
)