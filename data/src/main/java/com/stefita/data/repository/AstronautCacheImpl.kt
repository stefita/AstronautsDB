package com.stefita.data.repository

import com.stefita.data.db.AstronautDb
import com.stefita.data.entities.AstronautDataEntityMapper
import com.stefita.data.entities.AstronautEntityDataMapper
import com.stefita.domain.entities.AstronautEntity

class AstronautCacheImpl(database: AstronautDb) : AstronautDataStore {
    private val db = database.getAstronautsDao()
    private val astronautMapper = AstronautDataEntityMapper()
    private val astronautDataEntityMapper = AstronautEntityDataMapper()

    override suspend fun getAstronauts(): List<AstronautEntity> {
        return db.getAllAstronauts().map {
            astronautMapper.mapAstronautToEntity(it)
        }
    }

    override suspend fun getAstronauts(limit: Int, offset: Int): List<AstronautEntity> {
        return db.getAllAstronauts(limit, offset).map {
            astronautMapper.mapAstronautToEntity(it)
        }
    }

    suspend fun insertAstronauts(list: List<AstronautEntity>) {
        db.saveListAstronauts(list, astronautDataEntityMapper)
    }
}