package com.example.rickandmortycompose.ui.screens.locations.detailScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel


@Composable
fun LocationDetailScreen(
    locationId: Int,
    viewModel: LocationDetailViewModel = koinViewModel()
) {
    val location = viewModel.location.collectAsState().value

    LaunchedEffect(locationId) {
        viewModel.fetchLocationById(locationId)
    }

    if (location == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Name: ${location.name}")
            Text(text = "Type: ${location.type}")
            Text(text = "Dimension: ${location.dimension}")
        }
    }
}

