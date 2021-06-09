package com.example.learntesting.details

import com.example.learntesting.detailes.PlayListDetail
import com.example.learntesting.detailes.PlayListDetailFragmentViewModel
import com.example.learntesting.detailes.PlayListDetailService
import com.example.learntesting.utils.BaseUnitTes
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import petros.efthymiou.groovy.utils.getValueForTest
import java.lang.RuntimeException


class PlayListDetailsViewModelShould : BaseUnitTes(){

    lateinit var playListDetailFragmentViewModel: PlayListDetailFragmentViewModel
    private val id = "1"
    private val service : PlayListDetailService = mock()
    private val playListDetail : PlayListDetail = mock()
    private val expected = Result.success(playListDetail)
    private val exception = RuntimeException("Something went wrong")
    private val error = Result.failure<PlayListDetail>(exception)

    @ExperimentalCoroutinesApi
    @Test
    fun getPlayListDetailFromService() = runBlockingTest{
        mockSuccessfulCase()
        playListDetailFragmentViewModel.playListDetails.getValueForTest()
        verify(service, times(1)).fetchPlayListDetails(id)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun emitPlayListDetailFromService() = runBlockingTest{
        mockSuccessfulCase()
        assertEquals(expected,playListDetailFragmentViewModel.playListDetails.getValueForTest())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun emitErrorWhenServiceFails() = runBlockingTest{
       mockErrorCase()
        assertEquals(error,playListDetailFragmentViewModel.playListDetails.getValueForTest())
    }

    private suspend fun mockErrorCase() {
        whenever(service.fetchPlayListDetails(id)).thenReturn(
            flow {
                emit(error)
            }
        )
        playListDetailFragmentViewModel = PlayListDetailFragmentViewModel(service)
        playListDetailFragmentViewModel.getPlayListDetails(id)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(service.fetchPlayListDetails(id)).thenReturn(
            flow {
                emit(expected)
            }
        )
        playListDetailFragmentViewModel = PlayListDetailFragmentViewModel(service)
        playListDetailFragmentViewModel.getPlayListDetails(id)
    }


}