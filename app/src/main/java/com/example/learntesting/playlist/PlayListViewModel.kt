package com.example.learntesting.playlist

import androidx.lifecycle.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlayListViewModel(
    private val playListRepository : PlayListRepository
) : ViewModel(){

    val  playList = liveData {
        emitSource(playListRepository.getPlaylists().asLiveData())
    }

}
