package com.example.learntesting.detailes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class PlayListDetailFragmentViewModelFactory @Inject constructor(
    private val playListDetailService: PlayListDetailService
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlayListDetailFragmentViewModel(playListDetailService) as T
    }
}