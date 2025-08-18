package com.example.rickandmorty.ui.screens.episods.detailScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun EpisodeDetailScreen(
    episodeId: Int,
    viewModel: EpisodeDetailingViewModel = koinViewModel()
) {
    val episode = viewModel.episode.collectAsState().value

    LaunchedEffect(episodeId) {
        viewModel.fetchEpisodeById(episodeId)
    }

    if (episode == null) {
        Box(Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Name: ${episode.name}")
            Text(text = "Episode: ${episode.episode}")
            Text(text = "Air Date: ${episode.air_date}")
        }
    }
}


