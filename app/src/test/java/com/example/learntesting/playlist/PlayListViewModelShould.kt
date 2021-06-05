package com.example.learntesting.playlist

import com.example.learntesting.utils.BaseUnitTes
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import petros.efthymiou.groovy.utils.captureValues
import petros.efthymiou.groovy.utils.getValueForTest
import java.lang.RuntimeException

class PlayListViewModelShould : BaseUnitTes() {


    private val repository : PlayListRepository = mock()
    private val playList = mock<List<PlayList>>()
    private val expected = Result.success(playList)
    private val exception = RuntimeException("Something went wrong")

    @ExperimentalCoroutinesApi
    @Test
    fun getPlaylistsFromRepository() = runBlockingTest{
        val viewModel = mockSuccessfulCase()
        viewModel.playList.getValueForTest()
        verify(repository, times(1)).getPlaylists()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun showSpinnerWhileLoading() = runBlockingTest{
        val viewModel = mockSuccessfulCase()
        viewModel.loader.captureValues{
            viewModel.playList.getValueForTest()
            assertEquals(true,values[0])
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun closeLoaderAfterPlayListLoad() = runBlockingTest{
         val viewModel = mockErrorCase()
        viewModel.loader.captureValues{
            viewModel.playList.getValueForTest()
            assertEquals(false,values.last())
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun emitsPlayListsFromRepository() = runBlockingTest{
        var viewModel = mockSuccessfulCase()
        viewModel = mockSuccessfulCase()
        assertEquals(expected,viewModel.playList.getValueForTest())
    }


    @Test
    fun emitErrorWhenReceiveError(){
        val viewModel = mockErrorCase()
        assertEquals(exception,viewModel.playList.getValueForTest()!!.exceptionOrNull())
    }

    private fun mockErrorCase(): PlayListViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(Result.failure<List<PlayList>>(exception))
                }
            )
        }
        val viewModel = PlayListViewModel(repository)
        return viewModel
    }


    private fun mockSuccessfulCase(): PlayListViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        return PlayListViewModel(repository)
    }

}