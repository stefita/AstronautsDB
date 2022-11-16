package com.stefita.domain.repositories

import com.stefita.domain.entities.AstronautEntity

interface AstronautRepository {

    suspend fun getAstronauts(): List<AstronautEntity>

    suspend fun getAstronauts(limit: Int, offset: Int): List<AstronautEntity>

    suspend fun getAstronautById(astronautId: Int): AstronautEntity?

}