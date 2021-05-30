package com.example.learntesting.playlist

import kotlinx.coroutines.flow.Flow

class PlayListRepository {
    suspend fun getPlaylists() :Flow<Result<List<PlayList>>>{
        TODO("Not yet implemented")
    }

}
