package com.example.rickandmorty.data.dto.Episodes

data class EpisodeResponse(
    val results: List<EpisodesDTO>,
    val info: EpisodesPageInfo

)
