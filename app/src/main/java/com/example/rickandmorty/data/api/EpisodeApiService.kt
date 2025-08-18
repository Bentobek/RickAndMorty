package com.example.rickandmorty.data.api

import com.example.rickandmorty.data.dto.Episodes.EpisodeResponse
import com.example.rickandmorty.data.dto.Episodes.EpisodesDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeApiService {

    @GET("episode")
    suspend fun fetchEpisodes(
        @Query("page") page: Int
    ): Response<EpisodeResponse>

    @GET("episode/{id}")
    suspend fun getEpisodeById(@Path("id") id: Int): EpisodesDTO
}
