package com.example.learntesting.playlist

import androidx.lifecycle.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class PlayListViewModel(
    private val playListRepository : PlayListRepository
) : ViewModel(){

    val loader = MutableLiveData<Boolean>()

    val  playList = liveData {
        loader.postValue(true)
        emitSource(playListRepository.getPlaylists().onEach {
            loader.postValue(false)
        }.asLiveData())
    }

}
