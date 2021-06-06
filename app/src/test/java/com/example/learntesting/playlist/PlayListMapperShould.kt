package com.example.learntesting.playlist

import com.example.learntesting.R
import com.example.learntesting.utils.BaseUnitTes
import junit.framework.Assert.assertEquals
import org.junit.Test

class PlayListMapperShould : BaseUnitTes() {

 private val playListRaw = PlayListRaw("1","da name","da category")
 private val playListRawRock = PlayListRaw("1","da name","rock")
 private val mapper = PlayListMapper()
 private val playLists = mapper(listOf(playListRaw))
 private val playList = playLists[0]
 private val playListRock = mapper(listOf(playListRawRock))[0]

    @Test
    fun keepSameId(){
        assertEquals(playListRaw.id,playList.id)
    }

    @Test
    fun keepSameName(){
        assertEquals(playListRaw.name,playList.name)
    }

    @Test
    fun KeepSameCategory(){
        assertEquals(playListRaw.category,playList.category)
    }

    @Test
    fun mapDefaultImageWhenNotRock(){
        assertEquals(R.drawable.ic_play,playList.imageId)
    }

    @Test
    fun mapRockImageWhenRockCategory(){
        assertEquals(R.drawable.rock,playListRock.imageId)
    }
}