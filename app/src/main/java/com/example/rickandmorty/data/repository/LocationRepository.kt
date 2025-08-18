package com.example.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmorty.data.api.LocationApiService
import com.example.rickandmorty.data.dto.Locations.LocationDTO
import com.example.rickandmorty.data.paging.LocationPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LocationRepository(
    private val apiService: LocationApiService
) {

    fun fetchLocations(): Pager<Int, LocationDTO> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
                prefetchDistance = 5,
            ),
            pagingSourceFactory = { LocationPagingSource(apiService) }
        )
    }


    suspend fun fetchLocationById(id: Int): LocationDTO {
        return apiService.getLocationById(id)
    }

}
