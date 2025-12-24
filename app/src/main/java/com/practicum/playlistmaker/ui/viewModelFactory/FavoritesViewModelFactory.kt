package com.practicum.playlistmaker.ui.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practicum.playlistmaker.domain.TracksLocalRepository
import com.practicum.playlistmaker.ui.viewModel.FavoritesViewModel

class FavoritesViewModelFactory(
    private val tracksLocalRepository: TracksLocalRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoritesViewModel(tracksLocalRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}