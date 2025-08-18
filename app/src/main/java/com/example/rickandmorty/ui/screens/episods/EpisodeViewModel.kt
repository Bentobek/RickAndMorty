package com.example.rickandmorty.ui.screens.episods

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.data.dto.Episodes.EpisodesDTO
import com.example.rickandmorty.data.repository.EpisodeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EpisodeViewModel(
    private val repository: EpisodeRepository
) : ViewModel() {

    private val _episodes = MutableStateFlow<PagingData< EpisodesDTO>>(PagingData.empty())
    val episodes: MutableStateFlow<PagingData<EpisodesDTO>> = _episodes

    suspend fun fetchEpisodes() {
        repository.fetchEpisodes().flow.cachedIn(viewModelScope).collect {
            _episodes.value = it

        }
    }
}
