package com.example.rickandmorty.data.dto.Locations

data class LocationResponse(
    val results: List<LocationDTO>,
    val info: LocationPageInfo
)