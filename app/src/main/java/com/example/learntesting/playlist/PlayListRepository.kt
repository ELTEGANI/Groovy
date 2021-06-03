package com.example.learntesting.playlist

import android.util.Log
import kotlinx.coroutines.flow.Flow

class PlayListRepository(
    private val playListServices: PlayListServices
) {
    suspend fun getPlaylists() : Flow<Result<List<PlayList>>>{
       return playListServices.fetchPlayLists()
    }

}
