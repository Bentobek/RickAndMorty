package com.example.rickandmorty.ui.screens.episods.detailScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import coil.size.Scale
import com.example.rickandmorty.ui.components.DetailTopAppBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun EpisodeDetailScreen(
    episodeId: Int,
    navController: NavController,
    viewModel: EpisodeDetailingViewModel = koinViewModel()
) {
    val episode = viewModel.episode.collectAsState().value

    LaunchedEffect(episodeId) {
        viewModel.fetchEpisodeById(episodeId)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        DetailTopAppBar(
            title = episode?.name ?: "Episode Detail",
            onBackClick = { navController.popBackStack() }
    )
        if (episode == null) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
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
}

