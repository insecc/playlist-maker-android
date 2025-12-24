package com.practicum.playlistmaker.ui.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practicum.playlistmaker.domain.SearchHistoryRepository
import com.practicum.playlistmaker.domain.TracksRepository
import com.practicum.playlistmaker.ui.viewModel.SearchViewModel

class SearchViewModelFactory(
    private val repository: TracksRepository,
    private val searchHistoryRepository: SearchHistoryRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(repository, searchHistoryRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}