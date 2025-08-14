package com.example.rickandmortycompose.ui.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.data.local.FavoriteCharacterEntity
import com.example.rickandmortycompose.data.repository.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: FavoritesRepository
) : ViewModel() {

    val favorites: StateFlow<List<FavoriteCharacterEntity>> =
        repository.getFavorites().stateIn(
            viewModelScope,SharingStarted.Lazily,
            emptyList()
        )

    fun addFavorite(character: FavoriteCharacterEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFavorite(character)
        }
    }

    fun removeFavorite(character: FavoriteCharacterEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFavorite(character)
        }
    }

    fun isFavorite(id: Int): Boolean = repository.isFavorite(id)
}
