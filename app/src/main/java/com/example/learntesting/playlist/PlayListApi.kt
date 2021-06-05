package com.example.learntesting.playlist

import retrofit2.http.GET

interface PlayListApi {

    @GET("playlists")
    suspend fun fetchPlayLists() : List<PlayListRaw>

}
