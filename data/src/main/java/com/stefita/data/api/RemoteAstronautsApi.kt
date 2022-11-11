package com.stefita.data.api

import com.stefita.data.entities.AstronautData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface RemoteAstronautsApi {

    @Headers("Accept: application/json")
    @GET("characters")
    suspend fun getAstronauts(): Response<List<AstronautData>>
}