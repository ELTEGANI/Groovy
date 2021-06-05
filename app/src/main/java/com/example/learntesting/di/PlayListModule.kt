package com.example.learntesting.di

import com.example.learntesting.playlist.PlayListApi
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



val client = OkHttpClient()
val idlingResource = OkHttp3IdlingResource.create("okhttp", client)

@Module
@InstallIn(FragmentComponent::class)
class PlayListModule {

    @Provides
    fun playListApi(retrofit: Retrofit) = retrofit.create(PlayListApi::class.java)

    @Provides
    fun retrofit() = Retrofit.Builder()
        .baseUrl("https://my-json-server.typicode.com/ELTEGANI/testjson/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}