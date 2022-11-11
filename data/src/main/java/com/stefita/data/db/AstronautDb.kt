package com.stefita.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stefita.data.entities.AgencyData
import com.stefita.data.entities.AstronautData
import com.stefita.data.entities.StatusData
import com.stefita.data.entities.TypeData

@Database(entities = [
    AstronautData::class,
    AgencyData::class,
    TypeData::class,
    StatusData::class,
], version = 1, exportSchema = false)
abstract class AstronautDb : RoomDatabase() {
    abstract fun getAstronautsDao(): AstronautsDao
}