package com.example.rickandmortycompose.ui.screens.characters.detailScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel


@Composable
fun CharacterDetailScreen(
    characterId: Int,
    viewModel: CharacterDetailViewModel = koinViewModel()
) {
    val character = viewModel.character.collectAsState().value

    LaunchedEffect(characterId) {
        viewModel.fetchCharacterById(characterId)
    }

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


