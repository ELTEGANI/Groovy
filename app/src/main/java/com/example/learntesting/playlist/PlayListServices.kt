package com.example.learntesting.playlist

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException
import javax.inject.Inject


class PlayListServices @Inject constructor(
    private val playListApi: PlayListApi
) {

    suspend fun fetchPlaylists() : Flow<Result<List<PlayListRaw>>> {
        return flow {
            emit(Result.success(playListApi.fetchPlayLists()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

}
