package com.example.learntesting.di

import com.example.learntesting.playlist.PlayListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(FragmentComponent::class)
class PlayListModule {

    @Provides
    fun playListApi(retrofit: Retrofit) = retrofit.create(PlayListApi::class.java)

    @Provides
    fun retrofit() = Retrofit.Builder()
        .baseUrl("https://my-json-server.typicode.com/ELTEGANI/testjson/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}