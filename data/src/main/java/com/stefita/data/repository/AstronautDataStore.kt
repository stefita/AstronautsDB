package com.stefita.data.repository

import com.stefita.domain.entities.AstronautEntity


interface AstronautDataStore {
    suspend fun getAstronauts(): List<AstronautEntity>
}