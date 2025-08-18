package com.example.rickandmorty.ui.screens.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.data.dto.Locations.LocationDTO
import com.example.rickandmorty.data.repository.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationViewModel(
    private val repository: LocationRepository
) : ViewModel() {

    private val _locations = MutableStateFlow<PagingData<LocationDTO>>(PagingData.empty())
    val locations: StateFlow<PagingData<LocationDTO>> = _locations

    suspend fun fetchLocations() {
        repository.fetchLocations().flow.cachedIn(viewModelScope).collect{
            _locations.value = it
        }
    }
}

