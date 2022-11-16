package com.stefita.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.stefita.data.entities.AstronautData
import com.stefita.data.entities.AstronautEntityDataMapper
import com.stefita.domain.entities.AstronautEntity

@Dao
interface AstronautsDao {

    @Query("SELECT * FROM astronauts")
    suspend fun getAllAstronauts(): List<AstronautData>

    @Query("SELECT * FROM astronauts ORDER BY id LIMIT :limit OFFSET :offset")
    suspend fun getAllAstronauts(limit: Int, offset: Int): List<AstronautData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllAstronauts(astronauts: List<AstronautData>)

    @Transaction
    suspend fun saveListAstronauts(
        astronauts: List<AstronautEntity>,
        entityMapper: AstronautEntityDataMapper
    ) {
        val astronautsList = astronauts.map {
            entityMapper.mapAstronautToData(it)
        }

        saveAllAstronauts(astronautsList)
    }

    @Query("SELECT * FROM astronauts WHERE id = :astronautId")
    suspend fun getAstronautById(astronautId: Int): AstronautData?
}