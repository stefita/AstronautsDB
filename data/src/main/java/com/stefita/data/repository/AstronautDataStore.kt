package com.stefita.data.repository

import com.stefita.domain.entities.AstronautEntity


interface AstronautDataStore {
    suspend fun getAstronauts(): List<AstronautEntity>

    suspend fun getAstronauts(limit: Int, offset: Int): List<AstronautEntity>
}