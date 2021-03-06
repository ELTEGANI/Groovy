package com.example.learntesting.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlayListRepository @Inject constructor(
    private val service: PlayListServices,
    private val mapper: PlayListMapper
) {

    suspend fun getPlaylists() : Flow<Result<List<PlayList>>> =
        service.fetchPlaylists().map {
            if(it.isSuccess)
                Result.success(mapper(it.getOrNull()!!))
            else
                Result.failure(it.exceptionOrNull()!!)
        }

}
