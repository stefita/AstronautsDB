package com.stefita.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "statuses")
data class StatusData(
    @PrimaryKey
    val id: Int,
    val name: String
)