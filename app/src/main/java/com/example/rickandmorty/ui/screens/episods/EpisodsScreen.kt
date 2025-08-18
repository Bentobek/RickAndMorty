package com.example.rickandmorty.ui.screens.episods

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmorty.ui.components.LoadStateHandler
import org.koin.androidx.compose.koinViewModel

@Composable
fun EpisodsScreen(
    navController: NavController,
    viewModel: EpisodeViewModel = koinViewModel()
) {
    val episodes = viewModel.episodes.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.fetchEpisodes()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F2F2))
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()

        ) {
            items(episodes.itemCount) {
                val episode = episodes[it]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            episode?.id?.let { id ->
                                navController.navigate("episode_detail/$id")
                            }
                        },
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(6.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = episode?.name ?: "Unknown",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color(0xFF333333)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Episode: ${episode?.episode ?: "?"}",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color(0xFF333333)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Episode: ${episode?.episode ?: "?"}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Air date: ${episode?.air_date ?: "?"}",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.DarkGray
                        )
                    }
                }
                LoadStateHandler(
                    loadState = episodes.loadState.refresh,
                    onRetry = { episodes.retry() }
                )
            }
        }
    }
}

