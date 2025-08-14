package com.example.rickandmortycompose.data.repository

import com.example.rickandmortycompose.data.api.EpisodeApiService
import com.example.rickandmortycompose.data.dto.Episodes.EpisodesDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EpisodeRepository(
    private val apiService: EpisodeApiService
) {
    fun fetchEpisodes(): Flow<List<EpisodesDTO>> = flow {
        val response = apiService.fetchEpisodes()
        if (response.isSuccessful) {
            emit(response.body()?.results ?: emptyList())
        } else {
            emit(emptyList())
        }
    }.flowOn(Dispatchers.IO)

    suspend fun fetchEpisodesById(id: Int): EpisodesDTO {
        return apiService.getEpisodeById(id)
    }
}