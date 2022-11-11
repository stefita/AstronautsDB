package com.stefita.data.repository

import com.stefita.data.api.RemoteAstronautsApi
import com.stefita.data.entities.AstronautDataEntityMapper
import com.stefita.domain.entities.AstronautEntity

class AstronautsRemoteImpl constructor(private val api: RemoteAstronautsApi): AstronautDataStore{

    private val astronautMapper = AstronautDataEntityMapper()

    override suspend fun getAstronauts(): List<AstronautEntity> {
        val body = api.getAstronauts().body() ?: return emptyList()
        return body.map { astronautMapper.mapAstronautToEntity(it) }
    }
}