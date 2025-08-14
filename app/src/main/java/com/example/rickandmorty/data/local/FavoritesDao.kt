package com.example.rickandmortycompose.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favorite_characters")
    fun getAllFavorites(): Flow<List<FavoriteCharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(character: FavoriteCharacterEntity)

    @Delete
    suspend fun removeFavorite(character: FavoriteCharacterEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_characters WHERE id = :id)")
    fun isFavorite(id: Int): Boolean
}
