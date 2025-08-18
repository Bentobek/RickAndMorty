package com.example.rickandmorty.ui.navgation

sealed class NavScreens(val route: String) {
    object Characters : NavScreens("character_screen")
    object CharacterDetail : NavScreens("character_detail_screen/{characterId}")
        fun createRoute(characterId: Int) = "character_detail_screen/$characterId"

    object Locations : NavScreens("location_screen")
    object LocationDetail : NavScreens("location_detail/{locationId}") {
        fun passId(locationId: Int) = "location_detail/$locationId"
    }

    object Episodes : NavScreens("episode_screen")
    object EpisodeDetail : NavScreens("episode_detail_screen/{episodeId}") {
        fun passId(episodeId: Int) = "episode_detail_screen/$episodeId"
    }
    object Favorites : NavScreens("favorite_screen")
}
