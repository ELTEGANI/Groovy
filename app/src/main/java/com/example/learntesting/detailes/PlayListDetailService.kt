package com.example.learntesting.detailes

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlayListDetailService @Inject constructor() {

    suspend fun fetchPlayListDetails(s: String) : Flow<Result<PlayListDetail>>{
      TODO()
    }
}
