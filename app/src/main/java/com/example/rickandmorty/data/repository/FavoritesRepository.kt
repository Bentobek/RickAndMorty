package com.example.rickandmortycompose.data.repository

import com.example.rickandmortycompose.data.local.FavoriteCharacterEntity
import com.example.rickandmortycompose.data.local.FavoritesDao
import kotlinx.coroutines.flow.Flow

class FavoritesRepository(
    private val dao: FavoritesDao
) {

    fun getFavorites(): Flow<List<FavoriteCharacterEntity>> = dao.getAllFavorites()

    suspend fun addFavorite(character: FavoriteCharacterEntity) = dao.addFavorite(character)

    suspend fun removeFavorite(character: FavoriteCharacterEntity) = dao.removeFavorite(character)

    fun isFavorite(id: Int): Boolean = dao.isFavorite(id)
}