package com.example.rickandmortycompose.ui.screens.locations.detailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.data.dto.Locations.LocationDTO
import com.example.rickandmortycompose.data.repository.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationDetailViewModel(
    private val repository: LocationRepository
) : ViewModel() {

    private val _location = MutableStateFlow<LocationDTO?>(null)
    val location: StateFlow<LocationDTO?> = _location

    fun fetchLocationById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.fetchLocationById(id)
            _location.value = result
        }
    }
}

