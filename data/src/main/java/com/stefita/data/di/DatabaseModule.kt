package com.stefita.data.di

import androidx.room.Room
import com.stefita.data.db.AstronautDb
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {
    single{ Room.databaseBuilder(androidApplication(), AstronautDb::class.java, "astronauts").build() }
}
