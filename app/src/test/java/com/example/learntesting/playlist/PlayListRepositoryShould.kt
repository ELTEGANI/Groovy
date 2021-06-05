package com.example.learntesting.playlist

import com.example.learntesting.utils.BaseUnitTes
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.lang.RuntimeException

class PlayListRepositoryShould : BaseUnitTes(){

    private val playListServices : PlayListServices = mock()
    private val mapper : PlayListMapper = mock()
    private val playList  = mock<List<PlayList>>()
    private val playListRaw = mock<List<PlayListRaw>>()
    private val exception = RuntimeException("Something went wrong")


    @ExperimentalCoroutinesApi
    @Test
    fun getPlayListFromService() = runBlockingTest {
        val repository = mockSuccessfulCase()
        repository.getPlaylists()
        verify(playListServices,times(1)).fetchPlayLists()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun emitPlayListsFromService() = runBlockingTest {
        val repository = mockSuccessfulCase()
        assertEquals(playList,repository.getPlaylists().first().getOrNull())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun propagateErrors() = runBlockingTest{
      val repository = mockFailure()
        assertEquals(exception,repository.getPlaylists().first().exceptionOrNull())
    }


    @ExperimentalCoroutinesApi
    @Test
    fun delegateBusinessLoginToMapper() = runBlockingTest{
        val repository = mockSuccessfulCase()
         repository.getPlaylists().first()
         verify(mapper, times(1)).invoke(playListRaw)
    }

    private suspend fun mockFailure(): PlayListRepository {
        whenever(playListServices.fetchPlayLists()).thenReturn(
            flow {
                emit(Result.failure<List<PlayListRaw>>(exception))
            }
        )
        return PlayListRepository(playListServices,mapper)
    }


     private suspend fun mockSuccessfulCase(): PlayListRepository {
        whenever(playListServices.fetchPlayLists()).thenReturn(
            flow {
                emit(Result.success(playListRaw))
            }
        )
        whenever(mapper.invoke(playListRaw)).thenReturn(playList)
        return PlayListRepository(playListServices,mapper)
    }

}