package com.example.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmorty.data.api.EpisodeApiService
import com.example.rickandmorty.data.dto.Episodes.EpisodesDTO
import com.example.rickandmorty.data.paging.EpisodesPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EpisodeRepository(
    private val apiService: EpisodeApiService
) {
    fun fetchEpisodes(): Pager<Int, EpisodesDTO>{
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
                prefetchDistance = 5,
            ),
            pagingSourceFactory = { EpisodesPagingSource(apiService) }
        )
    }

    suspend fun fetchEpisodesById(id: Int): EpisodesDTO {
        return apiService.getEpisodeById(id)
    }
}