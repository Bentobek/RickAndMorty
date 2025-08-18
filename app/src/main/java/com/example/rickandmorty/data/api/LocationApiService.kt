package com.example.rickandmorty.data.api


import com.example.rickandmorty.data.dto.Locations.LocationDTO
import com.example.rickandmorty.data.dto.Locations.LocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationApiService {

    @GET("location")
    suspend fun fetchLocations(
        @Query("page") page: Int
    ): Response<LocationResponse>

    @GET("location/{id}")
    suspend fun getLocationById(@Path("id") id: Int): LocationDTO


}