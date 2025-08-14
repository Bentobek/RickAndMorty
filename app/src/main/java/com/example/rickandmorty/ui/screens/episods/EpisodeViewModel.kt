package com.example.rickandmortycompose.ui.screens.episods

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.data.dto.Episodes.EpisodesDTO
import com.example.rickandmortycompose.data.repository.EpisodeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EpisodeViewModel(
    private val repository: EpisodeRepository
) : ViewModel() {

    private val _episodes = MutableStateFlow<List< EpisodesDTO>>(emptyList())
    val episodes: StateFlow<List<EpisodesDTO>> = _episodes

    fun fetchEpisodes() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchEpisodes().collect { episodeList ->
                _episodes.value = episodeList
            }
        }
    }
}
