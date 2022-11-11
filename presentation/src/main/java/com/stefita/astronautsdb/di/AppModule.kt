package com.stefita.astronautsdb.di

import com.stefita.data.api.RemoteAstronautsApi
import com.stefita.data.repository.AstronautRepositoryImpl
import com.stefita.data.repository.AstronautsRemoteImpl
import com.stefita.astronautsdb.astrnauts.AstronautsViewModel
import com.stefita.astronautsdb.mapper.AstronautsEntityMapper
import com.stefita.domain.repositories.AstronautRepository
import com.stefita.domain.usecases.GetAstronautsUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {
    single { AstronautsRemoteImpl(api = get())}
    single { AstronautRepositoryImpl(remote = get()) as AstronautRepository }
}

val useCaseModule = module {
    factory { GetAstronautsUseCase(get()) }
}

val networkModules = module {
    single { createNetworkClient(BASE_URL) }
    single { (get() as Retrofit).create(RemoteAstronautsApi::class.java) }
}

val viewModels = module {
    viewModel {
        AstronautsViewModel(getAstronautsUseCase = get(), mapper = AstronautsEntityMapper())
    }
}

private const val BASE_URL = "https://lldev.thespacedevs.com"