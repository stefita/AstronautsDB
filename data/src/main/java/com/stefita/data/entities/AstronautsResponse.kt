package com.stefita.data.entities

data class AstronautsResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<AstronautData>
)