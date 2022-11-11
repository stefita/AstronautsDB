package com.stefita.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "types")
data class TypeData(
    @PrimaryKey
    val id: Int,
    val name: String
)