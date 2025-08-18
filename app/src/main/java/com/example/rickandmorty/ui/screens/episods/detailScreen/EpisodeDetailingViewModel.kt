package com.example.rickandmorty.ui.screens.episods.detailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.dto.Episodes.EpisodesDTO
import com.example.rickandmorty.data.repository.EpisodeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EpisodeDetailingViewModel(
    private val repository: EpisodeRepository
) : ViewModel() {

    private val _episode = MutableStateFlow<EpisodesDTO?>(null)
    val episode: StateFlow<EpisodesDTO?> = _episode

    fun fetchEpisodeById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.fetchEpisodesById(id)
            _episode.value = result
        }
    }
}
