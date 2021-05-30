package com.example.learntesting.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlayListViewModelFactory (
    private val playListRepository : PlayListRepository
        ): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlayListViewModel(playListRepository) as T
    }
}