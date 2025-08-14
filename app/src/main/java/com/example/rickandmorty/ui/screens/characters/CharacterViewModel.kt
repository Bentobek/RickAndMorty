package com.example.rickandmortycompose.ui.screens.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.data.dto.Characters.CharacterDTO
import com.example.rickandmortycompose.data.local.FavoriteCharacterEntity
import com.example.rickandmortycompose.data.repository.CharactersRepository
import com.example.rickandmortycompose.data.repository.FavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val repository: CharactersRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    private val _characters = MutableStateFlow<List<CharacterDTO>>(emptyList())
    val characters: StateFlow<List<CharacterDTO>> = _characters


 fun fetchCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCharactersWithFavorites().collect { list ->
                _characters.value = list
            }
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

