package com.example.rickandmortycompose.data.repository

import com.example.rickandmortycompose.data.api.LocationApiService
import com.example.rickandmortycompose.data.dto.Locations.LocationDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LocationRepository(
    private val apiService: LocationApiService
) {

    fun fetchLocations(): Flow<List<LocationDTO>> = flow {
        val response = apiService.fetchLocations()
        if (response.isSuccessful) {
            emit(response.body()?.results ?: emptyList())
        } else {
            emit(emptyList())
        }
    }.flowOn(Dispatchers.IO)

    suspend fun fetchLocationById(id: Int): LocationDTO {
        return apiService.getLocationById(id)
    }

}
