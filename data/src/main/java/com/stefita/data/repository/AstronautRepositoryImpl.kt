package com.stefita.data.repository

import com.stefita.domain.entities.AstronautEntity
import com.stefita.domain.repositories.AstronautRepository

class AstronautRepositoryImpl(private val remote: AstronautsRemoteImpl) : AstronautRepository {

    override suspend fun getAstronauts(): List<AstronautEntity> {
        return remote.getAstronauts()
    }
}