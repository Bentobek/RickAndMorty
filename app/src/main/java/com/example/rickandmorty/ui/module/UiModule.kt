package com.example.rickandmortycompose.ui.module

import com.example.rickandmortycompose.ui.screens.characters.detailScreen.CharacterDetailViewModel
import com.example.rickandmortycompose.ui.screens.characters.CharacterViewModel
import com.example.rickandmortycompose.ui.screens.episods.EpisodeViewModel
import com.example.rickandmortycompose.ui.screens.episods.detailScreen.EpisodeDetailingViewModel
import com.example.rickandmortycompose.ui.screens.favorite.FavoritesViewModel
import com.example.rickandmortycompose.ui.screens.locations.LocationViewModel
import com.example.rickandmortycompose.ui.screens.locations.detailScreen.LocationDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val uiModel = module {
    viewModel { CharacterViewModel(repository = get(), favoritesRepository = get()
    ) }
    viewModel { LocationViewModel(repository = get()) }
    viewModel { EpisodeViewModel(repository = get()) }
    viewModel { CharacterDetailViewModel(repository = get()) }
    viewModel { LocationDetailViewModel(repository = get()) }
    viewModel { EpisodeDetailingViewModel(repository = get()) }
    viewModel { FavoritesViewModel(repository = get()) }
}