package com.example.rickandmorty.data.dto.Locations

data class LocationDTO(
    val id: Int? = null,
    val name: String? = null,
    val type: String? = null,
    val dimension: String? = null,
    val residents: List<String>? = null,
    val url: String? = null,
    val created: String? = null

)