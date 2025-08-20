package com.example.rickandmorty.ui.screens.characters.detailScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.rickandmorty.ui.components.DetailTopAppBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterDetailScreen(
    characterId: Int,
    navController: NavController,
    viewModel: CharacterDetailViewModel = koinViewModel()
) {
    val character = viewModel.character.collectAsState().value

    LaunchedEffect(characterId) {
        viewModel.fetchCharacterById(characterId)
    }
    Column(
        modifier = Modifier.
        fillMaxSize()
    ) {
    DetailTopAppBar(
        title = character?.name ?: "Character Detail",
        onBackClick = { navController.popBackStack() }
    )
        if (character == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Name: ${character.name}")
            Text(text = "Status: ${character.status}")
            Text(text = "Species: ${character.species}")
            Text(text = "Gender: ${character.gender}")
            Text(text = "Origin: ${character.origin?.name}")
            Text(text = "Location: ${character.location?.name}")
            }
        }
    }
}


