package com.example.learntesting.detailes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learntesting.playlist.PlayList
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlayListDetailFragmentViewModel @Inject constructor(private val playListDetailService: PlayListDetailService) : ViewModel(){

    val playListDetails: MutableLiveData<Result<PlayListDetail>> = MutableLiveData()

    fun getPlayListDetails(id: String) {
        viewModelScope.launch {
            playListDetailService.fetchPlayListDetails(id).collect {
                playListDetails.postValue(it)
            }
        }
    }

}
