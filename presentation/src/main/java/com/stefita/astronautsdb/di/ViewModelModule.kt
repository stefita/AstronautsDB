package com.stefita.astronautsdb.di

import com.stefita.astronautsdb.mapper.AstronautsEntityMapper
import com.stefita.astronautsdb.ui.astronautdetails.AstronautDetailsViewModel
import com.stefita.astronautsdb.ui.astronautslist.AstronautsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModels = module {
    viewModel {
        AstronautsViewModel(getAstronautsUseCase = get(), mapper = AstronautsEntityMapper())
    }
    viewModel {
        AstronautDetailsViewModel(
            getAstronautByIdUseCase = get(),
            mapper = AstronautsEntityMapper()
        )
    }
}

