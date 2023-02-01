package com.stefita.data.api

import com.stefita.data.entities.AstronautData
import com.stefita.data.entities.AstronautsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteAstronautsApi {

    @Headers("Accept: application/json")
    @GET("astronaut/")
    suspend fun getAstronauts(): Response<AstronautsResponse>

    @Headers("Accept: application/json")
    @GET("astronaut/")
    suspend fun getAstronauts(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("ordering") ordering: String = "id"
    ): Response<AstronautsResponse>

    @Headers("Accept: application/json")
    @GET("astronaut/{id}/")
    suspend fun getAstronautById(
        @Path("id") id: Int
    ): Response<AstronautData>
}