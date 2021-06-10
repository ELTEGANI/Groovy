package com.example.learntesting.detailes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learntesting.playlist.PlayList
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlayListDetailFragmentViewModel @Inject constructor(private val playListDetailService: PlayListDetailService) : ViewModel(){

    val playListDetails: MutableLiveData<Result<PlayListDetail>> = MutableLiveData()
    val loader = MutableLiveData<Boolean>()

    fun getPlayListDetails(id: String) {
        viewModelScope.launch {
            loader.postValue(true)
            playListDetailService.fetchPlayListDetails(id)
                .onEach {
                    loader.postValue(false)
                }
                .collect {
                playListDetails.postValue(it)
            }
        }
    }

}
