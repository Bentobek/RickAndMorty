package com.example.rickandmorty.data.api


import com.example.rickandmorty.data.dto.Characters.CharacterDTO
import com.example.rickandmorty.data.dto.Characters.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApiService {

    @GET("character")
    suspend fun fetchCharacters(
        @Query("page") pages: Int
    ): Response<CharactersResponse>


    @GET("character/{id}")
    suspend fun fetchCharacterById(@Path("id") id: Int): Response<CharacterDTO>

}