package com.example.rickandmortycompose.ui.screens.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.data.dto.Locations.LocationDTO
import com.example.rickandmortycompose.data.repository.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocationViewModel(
    private val repository: LocationRepository
) : ViewModel() {

    private val _locations = MutableStateFlow<List<LocationDTO>>(emptyList())
    val locations: StateFlow<List<LocationDTO>> = _locations

    fun fetchLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchLocations().collect {
                _locations.value = it
            }
        }
    }
}

