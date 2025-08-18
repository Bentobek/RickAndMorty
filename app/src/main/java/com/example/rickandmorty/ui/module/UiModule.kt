package com.example.rickandmorty.ui.module

import com.example.rickandmorty.ui.screens.characters.CharacterViewModel
import com.example.rickandmorty.ui.screens.characters.detailScreen.CharacterDetailViewModel
import com.example.rickandmorty.ui.screens.episods.EpisodeViewModel
import com.example.rickandmorty.ui.screens.episods.detailScreen.EpisodeDetailingViewModel
import com.example.rickandmorty.ui.screens.favorite.FavoritesViewModel
import com.example.rickandmorty.ui.screens.locations.LocationViewModel
import com.example.rickandmorty.ui.screens.locations.detailScreen.LocationDetailViewModel
import org.koin.core.module.dsl.viewModel
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