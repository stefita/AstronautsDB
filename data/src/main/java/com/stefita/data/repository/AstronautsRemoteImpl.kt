package com.stefita.data.repository

import android.util.Log
import com.stefita.data.api.RemoteAstronautsApi
import com.stefita.data.entities.AstronautDataEntityMapper
import com.stefita.domain.entities.AstronautEntity

class AstronautsRemoteImpl constructor(private val api: RemoteAstronautsApi): AstronautDataStore {

    private val astronautMapper = AstronautDataEntityMapper()

    override suspend fun getAstronauts(): List<AstronautEntity> {
        val body = api.getAstronauts().body()?.results ?: return emptyList()
        return body.mapNotNull { astronautMapper.mapAstronautToEntity(it) }
    }

    override suspend fun getAstronauts(limit: Int, offset: Int): List<AstronautEntity> {
        val body = api.getAstronauts(
            limit,
            limit * offset
        ).body()?.results ?: return emptyList()

        return body.mapNotNull { astronautMapper.mapAstronautToEntity(it) }
    }

    override suspend fun getAstronautById(id: Int): AstronautEntity? {
        val body = api.getAstronautById(id).body()
        return astronautMapper.mapAstronautToEntity(body)
    }
}