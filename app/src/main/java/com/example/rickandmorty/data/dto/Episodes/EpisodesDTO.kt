package com.example.rickandmortycompose.data.dto.Episodes

data class EpisodesDTO(
    val id: Int? = null,
    val name: String? = null,
    val air_date: String? = null,
    val episode: String? = null,
    val characters: List<String>? = null,
    val url: String? = null,
    val created: String? = null
)