package com.example.rickandmorty.data.dto.Characters


data class CharacterDTO(
    val id: Int? = null,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val type: String? = null,
    val gender: String? = null,
    val origin: OriginDTO? = null,
    val image: String? = null,
    val created: String? = null,
    val location: LocationDto? = null,
    val isFavorite: Boolean = false,

)
data class OriginDTO(
    val name: String? = null,
    val url: String? = null,
)
data class LocationDto(
    val name: String? = null,
    val url: String? = null,
)



