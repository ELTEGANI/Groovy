package com.example.learntesting.playlist

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException


class PlayListServices(
    private val playListApi: PlayListApi
) {

    suspend fun fetchPlayLists() : Flow<Result<List<PlayList>>>{
        return flow {
              emit(Result.success(playListApi.fetchPlayLists()))
            Log.d("lists",Result.success(playListApi.fetchPlayLists()).toString())
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
            Log.d("lists",Result.success(playListApi.fetchPlayLists()).toString())
        }
    }

}
