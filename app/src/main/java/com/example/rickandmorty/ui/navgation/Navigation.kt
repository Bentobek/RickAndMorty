package com.example.rickandmortycompose.ui.navgation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmortycompose.ui.navgation.bottomNavBar.BottomNavBar
import com.example.rickandmortycompose.ui.screens.characters.detailScreen.CharacterDetailScreen
import com.example.rickandmortycompose.ui.screens.characters.CharacterScreen
import com.example.rickandmortycompose.ui.screens.episods.EpisodsScreen
import com.example.rickandmortycompose.ui.screens.episods.detailScreen.EpisodeDetailScreen
import com.example.rickandmortycompose.ui.screens.favorite.FavoritesScreen
import com.example.rickandmortycompose.ui.screens.locations.LocationsScreen
import com.example.rickandmortycompose.ui.screens.locations.detailScreen.LocationDetailScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            navController = navController,
            startDestination = NavScreens.Characters.route
        ) {
            composable(NavScreens.Characters.route) {
                CharacterScreen(
                    navController = navController
                )
            }
            composable(NavScreens.Locations.route) {
                LocationsScreen(
                    navController = navController
                )

            }
            composable(NavScreens.Episodes.route) {
                EpisodsScreen(
                    navController = navController,
                )
            }
            composable(NavScreens.Favorites.route) {
                FavoritesScreen(
                    navController = navController
                )
            }
            composable(
                route = "character_detail/{characterId}",
                arguments = listOf(navArgument("characterId") { type = NavType.IntType })
            ) { backStackEntry ->
                val characterId = backStackEntry.arguments?.getInt("characterId") ?: return@composable
                CharacterDetailScreen(characterId = characterId)
                }
//            composable(
//                route = "location_detail/{locationId}",
//                arguments = listOf(navArgument("locationId") { type = NavType.IntType })
//            ) { backStackEntry ->
//                val locationId = backStackEntry.arguments?.getInt("locationId") ?: return@composable
//                LocationDetailScreen(
//                    locationId = locationId,
//                    navController = navController
//                )
//            }
            composable("location_detail/{locationId}") { backStackEntry ->
                val locationId = backStackEntry.arguments?.getString("locationId")?.toIntOrNull()
                locationId?.let {
                    LocationDetailScreen(
                        locationId = it,
                    )
                }
            }

            composable("episode_detail/{episodeId}") { backStackEntry ->
                val episodeId = backStackEntry.arguments?.getString("episodeId")?.toIntOrNull()
                episodeId?.let {
                    EpisodeDetailScreen(
                        episodeId = it,
                    )
                }
            }
        }
    }
}

