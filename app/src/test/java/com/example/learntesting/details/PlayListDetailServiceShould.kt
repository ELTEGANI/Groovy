package com.example.learntesting.details

import com.example.learntesting.detailes.PlayListDetail
import com.example.learntesting.detailes.PlayListDetailApi
import com.example.learntesting.detailes.PlayListDetailService
import com.example.learntesting.utils.BaseUnitTes
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class PlayListDetailServiceShould : BaseUnitTes(){

    lateinit var playListDetailService: PlayListDetailService
    private val id = "100"
    private val api : PlayListDetailApi = mock()
    private val playListDetail : PlayListDetail = mock()
    private val exception = RuntimeException("Damn backend developers")

    @ExperimentalCoroutinesApi
    @Test
    fun fetchPlayListDetailsFromApi() = runBlockingTest {
        mockSuccesfullCase()
        playListDetailService.fetchPlayListDetails(id).single()
        verify(api, times(1)).fetchPlayListDetails(id)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun convertValuesToFlowResultAndEmitThem() = runBlockingTest{
        mockSuccesfullCase()
        assertEquals(Result.success(playListDetail),playListDetailService.fetchPlayListDetails(id).single())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun emitErrorResultWhenNetworkFails() = runBlockingTest{
       mockErrorCase()
        assertEquals("Something went wrong"
        ,playListDetailService.fetchPlayListDetails(id).single().exceptionOrNull()?.message
        )
    }

    private suspend fun mockErrorCase() {
        whenever(api.fetchPlayListDetails(id)).thenThrow(exception)
        playListDetailService = PlayListDetailService(api)
    }

    private suspend fun mockSuccesfullCase() {
        whenever(api.fetchPlayListDetails(id)).thenReturn(playListDetail)
        playListDetailService = PlayListDetailService(api)
    }

}