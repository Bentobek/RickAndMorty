package com.example.rickandmorty.data.paging

import androidx.core.net.toUri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.data.api.EpisodeApiService
import com.example.rickandmorty.data.dto.Episodes.EpisodesDTO

class EpisodesPagingSource(
    val apiService: EpisodeApiService,
): PagingSource<Int, EpisodesDTO>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodesDTO> {
        return try {
            val page = params.key ?: 1

            val response = apiService.fetchEpisodes(page)

            if (response.isSuccessful) {
                val body = response.body()
                val results = body?.results ?: emptyList()

                val nextPage = body?.info?.next?.toUri()
                    ?.getQueryParameter("page")
                    ?.toInt()

                val prevPage = body?.info?.prev?.toUri()
                    ?.getQueryParameter("page")
                    ?.toInt()

                LoadResult.Page(
                    data = results,
                    prevKey = prevPage,
                    nextKey = nextPage
                )
            } else {
                LoadResult.Error(Exception("Network error: ${response.code()}"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, EpisodesDTO>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}