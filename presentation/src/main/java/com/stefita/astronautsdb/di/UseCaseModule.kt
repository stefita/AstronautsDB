package com.stefita.astronautsdb.di

import com.stefita.domain.usecases.GetAstronautByIdUseCase
import com.stefita.domain.usecases.GetAstronautsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetAstronautsUseCase(get()) }
    factory { GetAstronautByIdUseCase(get()) }
}