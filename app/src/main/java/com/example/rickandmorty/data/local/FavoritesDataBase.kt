package com.example.rickandmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteCharacterEntity::class], version = 1)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}
