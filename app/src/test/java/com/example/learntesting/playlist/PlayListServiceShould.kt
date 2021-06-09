package com.example.learntesting.playlist

import com.example.learntesting.utils.BaseUnitTes
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class PlayListServiceShould : BaseUnitTes(){

    private lateinit var playListServices: PlayListServices
    private val playListApi : PlayListApi = mock()
    private val playList : List<PlayListRaw> = mock()

    @ExperimentalCoroutinesApi
    @Test
    fun fetchPlayServiceFromApi() = runBlockingTest {
        playListServices = PlayListServices(playListApi)
        playListServices.fetchPlaylists().first()
        verify(playListApi, times(1)).fetchPlayLists()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun convertValuesOfResultAndEmitsThem() = runBlockingTest {
        mockSuccessfulCase()
        assertEquals(Result.success(playList),playListServices.fetchPlaylists().first())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun emitErrorResultWhenNetworkFails() = runBlockingTest{
       mockFailerCase()
        assertEquals("Something went wrong",playListServices.fetchPlaylists().first().exceptionOrNull()?.message)
    }

    private suspend fun mockFailerCase() {
        whenever(playListApi.fetchPlayLists()).thenThrow(RuntimeException("Damn Backend Developer"))
        playListServices = PlayListServices(playListApi)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(playListApi.fetchPlayLists()).thenReturn(playList)
        playListServices = PlayListServices(playListApi)
    }

}