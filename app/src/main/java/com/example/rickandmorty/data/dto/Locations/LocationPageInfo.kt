package com.example.rickandmorty.data.dto.Locations

data class LocationPageInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)
