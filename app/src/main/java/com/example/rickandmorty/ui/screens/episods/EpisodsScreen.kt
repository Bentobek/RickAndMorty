package com.example.rickandmorty.ui.screens.episods

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmorty.data.dto.Episodes.EpisodesDTO
import com.example.rickandmorty.ui.components.LoadStateHandler
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodsScreen(
    navController: NavController,
    viewModel: EpisodeViewModel = koinViewModel(),
    isRefreshing: Boolean,
    onRefresh: () -> Unit

) {

    val episodes = viewModel.episodes.collectAsLazyPagingItems()
    val pullRefreshState = rememberPullToRefreshState()


    LaunchedEffect(Unit) {
        viewModel.fetchEpisodes()
    }


    PullToRefreshBox(
        state = pullRefreshState,
        onRefresh = onRefresh,
        isRefreshing = isRefreshing,
        modifier = Modifier.fillMaxSize(),
        indicator = {
            Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                color = MaterialTheme.colorScheme.primary,
                state = pullRefreshState,
            )
        }
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()


        ) {
            items(episodes.itemCount) {
                val episode = episodes[it]
                EpisodeItem(
                    episode = episode,
                    onClick = {
                        episode?.id?.let { id ->
                            navController.navigate("episode_detail/$id")
                        }
                    }
                )
                LoadStateHandler(
                    loadState = episodes.loadState.refresh,
                    onRetry = { episodes.retry() }
                )
               }
            }
        }
    }
@Composable
fun EpisodeItem(
    episode: EpisodesDTO?,
    onClick: () -> Unit
){
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()

    val scale = animateFloatAsState(
        targetValue = if (isPressed.value) 1f else 0.9f,
        label = "scaleAnim"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                scaleX = scale.value
                scaleY = scale.value
            }
            .clickable(
                interactionSource = interactionSource
                ,
                indication = null,
                onClick = onClick
            )
        ,
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
}


