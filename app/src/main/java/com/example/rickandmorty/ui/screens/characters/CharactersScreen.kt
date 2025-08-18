package com.example.rickandmorty.ui.screens.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.rickandmorty.data.dto.Characters.CharacterDTO
import com.example.rickandmorty.ui.components.LoadStateHandler
import org.koin.androidx.compose.koinViewModel

@Composable
fun CharacterScreen(
    navController: NavController,
    viewModel: CharacterViewModel = koinViewModel()
) {
    val characters = viewModel.charactersStateFlow.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.fetchCharacters()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(8.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(characters.itemCount) {
                val character = characters[it]
                CharacterItem(
                    character = character,
                    onClick = {
                        character?.id?.let { id ->
                            navController.navigate("character_detail/$id")
                        }
                    },
                    onFavoriteClick = { selectedCharacter ->
                        viewModel.addToFavorites(selectedCharacter)
                    }
                )
                LoadStateHandler(
                    loadState = characters.loadState.refresh,
                    onRetry = { characters.retry() }
                )
            }
        }
    }
}
@Composable
fun CharacterItem(
    character: CharacterDTO?,
    onClick: () -> Unit,
    onFavoriteClick: (CharacterDTO) -> Unit
    ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
            AsyncImage(
                model = character?.image,
                contentDescription = null,
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = character?.name ?: "Unknown", style = MaterialTheme.typography.titleMedium)
                Text(text = "Status: ${character?.status}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Species: ${character?.species}", style = MaterialTheme.typography.bodySmall)
            }
            IconButton(
                onClick = {
                    if (character != null) {
                        onFavoriteClick(character)
                    }
                }
            ) {
                Icon(
                    imageVector = if (character?.isFavorite == true) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Toggle Favorite",
                    tint = if (character?.isFavorite == true) Color.Red else Color.Gray
                )
            }
        }
    }
}
