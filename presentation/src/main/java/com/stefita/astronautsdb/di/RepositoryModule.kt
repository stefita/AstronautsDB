package com.stefita.astronautsdb.di

import com.stefita.data.repository.AstronautCacheImpl
import com.stefita.data.repository.AstronautRepositoryImpl
import com.stefita.data.repository.AstronautsRemoteImpl
import com.stefita.domain.repositories.AstronautRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AstronautsRemoteImpl(api = get()) }
    single { AstronautCacheImpl(database = get()) }
    single { AstronautRepositoryImpl(remote = get(), local = get()) as AstronautRepository }
}