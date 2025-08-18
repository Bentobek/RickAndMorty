package com.example.rickandmorty.ui.screens.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.data.dto.Characters.CharacterDTO
import com.example.rickandmorty.data.local.FavoriteCharacterEntity
import com.example.rickandmorty.data.repository.CharactersRepository
import com.example.rickandmorty.data.repository.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val repository: CharactersRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _characters = MutableStateFlow<PagingData<CharacterDTO>>(PagingData.empty())
    val charactersStateFlow: StateFlow<PagingData<CharacterDTO>> = _characters.asStateFlow()

    suspend fun fetchCharacters() {
        repository
            .fetchCharacters()
            .flow
            .cachedIn(viewModelScope)
            .collectLatest {
                _characters.value = it
            }
    }

    fun toggleFavorite(character: CharacterDTO) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.toggleFavorite(character)
        }
    }
    fun addToFavorites(character: CharacterDTO) {
        viewModelScope.launch(Dispatchers.IO) {
            favoritesRepository.addFavorite(FavoriteCharacterEntity(
                id = character.id ?: 0,
                name = character.name ?: "",
                status = character.status ?: "",
                species = character.species ?: "",
                gender = character.gender ?: "",
                image = character.image ?: "",
            ))
        }
    }
}

