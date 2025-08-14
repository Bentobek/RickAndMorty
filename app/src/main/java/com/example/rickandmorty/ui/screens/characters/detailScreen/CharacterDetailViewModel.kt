package com.example.rickandmortycompose.ui.screens.characters.detailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycompose.data.dto.Characters.CharacterDTO
import com.example.rickandmortycompose.data.repository.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    private val repository: CharactersRepository
) : ViewModel() {

    private val _character = MutableStateFlow<CharacterDTO?>(null)
    val character: StateFlow<CharacterDTO?> = _character

    fun fetchCharacterById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.fetchCharacterById(id)
            _character.value = result
        }
    }
}
