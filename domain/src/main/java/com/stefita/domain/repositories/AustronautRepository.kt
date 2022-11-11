package com.stefita.domain.repositories

import com.stefita.domain.entities.AstronautEntity

interface AstronautRepository {

    suspend fun getAstronauts(): List<AstronautEntity>
}