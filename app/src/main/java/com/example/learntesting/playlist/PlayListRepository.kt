package com.example.learntesting.playlist

import android.util.Log
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlayListRepository @Inject constructor(
    private val playListServices: PlayListServices
) {
    suspend fun getPlaylists() : Flow<Result<List<PlayList>>>{
       return playListServices.fetchPlayLists()
    }

}
