package com.example.rickandmortycompose.data.api


import com.example.rickandmortycompose.data.dto.Locations.LocationDTO
import com.example.rickandmortycompose.data.dto.Locations.LocationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationApiService {

    @GET("location")
    suspend fun fetchLocations(): Response<LocationResponse>

    @GET("location/{id}")
    suspend fun getLocationById(@Path("id") id: Int): LocationDTO


}