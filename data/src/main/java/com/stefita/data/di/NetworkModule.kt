package com.stefita.data.di

import com.stefita.data.network.createNetworkClient
import com.stefita.data.api.RemoteAstronautsApi
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModules = module {
    single { createNetworkClient(BASE_URL) }
    single { (get() as Retrofit).create(RemoteAstronautsApi::class.java) }
}

private const val BASE_URL = "https://lldev.thespacedevs.com/2.2.0/"