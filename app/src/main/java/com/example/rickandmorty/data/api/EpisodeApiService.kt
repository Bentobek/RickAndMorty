package com.example.rickandmortycompose.data.api

import com.example.rickandmortycompose.data.dto.Episodes.EpisodeResponse
import com.example.rickandmortycompose.data.dto.Episodes.EpisodesDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeApiService {

    @GET("episode")
    suspend fun fetchEpisodes(): Response<EpisodeResponse>

    @GET("episode/{id}")
    suspend fun getEpisodeById(@Path("id") id: Int): EpisodesDTO
}
