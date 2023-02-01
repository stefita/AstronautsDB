package com.stefita.data.repository

import android.util.Log
import com.stefita.domain.entities.AstronautEntity
import com.stefita.domain.repositories.AstronautRepository

class AstronautRepositoryImpl(
    private val remote: AstronautsRemoteImpl,
    private val local: AstronautCacheImpl
) : AstronautRepository {

    override suspend fun getAstronauts(): List<AstronautEntity> {
        val cachedAstronauts = getCachedAstronauts()

        return if (cachedAstronauts.isEmpty().not()) {
            cachedAstronauts
        } else {
            val remoteAstronauts = getRemoteAstronauts()
            local.insertAstronauts(remoteAstronauts)
            remoteAstronauts
        }
    }

    override suspend fun getAstronauts(limit: Int, offset: Int): List<AstronautEntity> {
        val cachedAstronauts = getCachedAstronauts(limit, offset)

        return if (cachedAstronauts.isEmpty().not()) {
            cachedAstronauts
        } else {
            val remoteAstronauts =  getRemoteAstronauts(limit, offset)
            local.insertAstronauts(remoteAstronauts)
            return remoteAstronauts
        }
    }

    override suspend fun getAstronautById(astronautId: Int): AstronautEntity? {
        return local.getAstronautById(astronautId)
    }

    private suspend fun getRemoteAstronauts(): List<AstronautEntity> {
        return remote.getAstronauts()
    }

    private suspend fun getRemoteAstronauts(limit: Int, offset: Int): List<AstronautEntity> {
        return remote.getAstronauts(limit, offset)
    }

    private suspend fun getCachedAstronauts(): List<AstronautEntity> {
        return local.getAstronauts()
    }

    private suspend fun getCachedAstronauts(limit: Int, offset: Int): List<AstronautEntity> {
        return local.getAstronauts(limit, offset)
    }
}