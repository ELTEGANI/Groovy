package com.example.learntesting.detailes

import retrofit2.http.GET
import retrofit2.http.Path

interface PlayListDetailApi {

    @GET("details/{id}")
    suspend fun fetchPlayListDetails(@Path("id") id: String) : PlayListDetail

}
