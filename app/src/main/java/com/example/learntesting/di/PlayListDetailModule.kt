package com.example.learntesting.di

import com.example.learntesting.detailes.PlayListDetailApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit


@Module
@InstallIn(FragmentComponent::class)
class PlayListDetailModule {
    @Provides
    fun PlayListDetailApi(retrofit: Retrofit): PlayListDetailApi = retrofit.create(PlayListDetailApi::class.java)

}