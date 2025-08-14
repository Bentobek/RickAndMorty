package com.example.rickandmortycompose.data.repository

import com.example.rickandmortycompose.data.api.CharactersApiService
import com.example.rickandmortycompose.data.dto.Characters.CharacterDTO
import com.example.rickandmortycompose.data.local.FavoriteCharacterEntity
import com.example.rickandmortycompose.data.local.FavoritesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class CharactersRepository(
    private val apiService: CharactersApiService,
    private val favoritesDao: FavoritesDao
) {

    suspend fun fetchCharacterById(id: Int): CharacterDTO? {
        val response = apiService.fetchCharacterById(id)

        if (response.isSuccessful) {
            val dto = response.body()
            val favorites = favoritesDao.getAllFavorites()
                .map { list -> list.map { it.id } }
                .first()

            return dto?.copy(isFavorite = favorites.contains(dto.id))
        }

        return null
    }

    fun getCharactersWithFavorites(): Flow<List<CharacterDTO>> = favoritesDao.getAllFavorites()
        .map { favoritesList ->
            val favoriteIds = favoritesList.map { it.id }.toSet()
            val response = apiService.fetchCharacters()
            if (response.isSuccessful) {
                response.body()?.results?.map { dto ->
                    dto.copy(isFavorite = favoriteIds.contains(dto.id))
                } ?: emptyList()
            } else {
                emptyList()
            }
        }

    suspend fun toggleFavorite(character: CharacterDTO) {
        val id = character.id ?: return
        val isFav = favoritesDao.isFavorite(id)

        val entity = FavoriteCharacterEntity(
            id = id,
            name = character.name ?: "Unknown",
            gender = character.gender ?: "Unknown",
            image = character.image ?: "",
            status = character.status ?: "Unknown",
            species = character.species ?: "Unknown"
        )

        if (isFav) {
            favoritesDao.removeFavorite(entity)
        } else {
            favoritesDao.addFavorite(entity)
        }
    }
}


