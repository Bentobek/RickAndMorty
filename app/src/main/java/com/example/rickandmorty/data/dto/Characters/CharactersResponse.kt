package com.example.rickandmorty.data.dto.Characters



data class CharactersResponse (
    val results: List<CharacterDTO>,
    val info: PageInfo
)