package com.example.learntesting.detailes

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException
import javax.inject.Inject

class PlayListDetailService @Inject constructor(
    private val playListDetailApi: PlayListDetailApi
) {

    suspend fun fetchPlayListDetails(id: String) : Flow<Result<PlayListDetail>>{
      return flow {
          emit(Result.success(playListDetailApi.fetchPlayListDetails(id)))
      }.catch {
          emit(Result.failure(RuntimeException("Something went wrong")))
      }
    }
}
