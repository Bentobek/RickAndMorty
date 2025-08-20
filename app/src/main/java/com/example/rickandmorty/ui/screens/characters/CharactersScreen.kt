package com.example.rickandmorty.ui.screens.characters

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.rickandmorty.data.dto.Characters.CharacterDTO
import com.example.rickandmorty.ui.components.LoadStateHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterScreen(
    navController: NavController,
    viewModel: CharacterViewModel = koinViewModel(),
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
) {

    var backgroundStatus = remember { mutableStateOf(false) }
    val animateBackground = animateColorAsState(
        targetValue = if (backgroundStatus.value)Color.Red else Color.Blue
    )


    val characters = viewModel.charactersStateFlow.collectAsLazyPagingItems()

    val pullRefreshState = rememberPullToRefreshState()

    LaunchedEffect(Unit) {
        launch {
            viewModel.fetchCharacters()
        }
       launch{
           while(true){
            delay(1000)
            backgroundStatus.value = !backgroundStatus.value
           }
        }
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
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(animateBackground.value),
            verticalArrangement = Arrangement.spacedBy(16.dp)


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


    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()

    val scale = animateFloatAsState(
        targetValue = if (isPressed.value) 1f else 0.9f,
        label = "scaleAnim"
    )

//    val interactionSource = remember { MutableInteractionSource() }
//    val isPressed = interactionSource.collectIsPressedAsState()
//    val animWidth = animateDpAsState(
//        targetValue = if (isPressed.value) 200.dp else 140.dp,
//        label = "widthAnim"
//    )
//    val animHeight = animateDpAsState(
//        targetValue = if (isPressed.value) 140.dp else 120.dp,
//        label = "heightAnim"
//    )

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
