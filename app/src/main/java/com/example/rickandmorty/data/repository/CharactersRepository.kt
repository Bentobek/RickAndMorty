package com.example.rickandmorty.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmorty.data.api.CharactersApiService
import com.example.rickandmorty.data.dto.Characters.CharacterDTO
import com.example.rickandmorty.data.local.FavoriteCharacterEntity
import com.example.rickandmorty.data.local.FavoritesDao
import com.example.rickandmorty.data.paging.CharactersPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlin.io.path.Path

class CharactersRepository(
    private val apiService: CharactersApiService,
    private val favoritesDao: FavoritesDao
) {

    fun fetchCharacters(): Pager<Int,CharacterDTO> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
                prefetchDistance = 5,
            ),
            pagingSourceFactory = { CharactersPagingSource(apiService) }
        )
    }


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


