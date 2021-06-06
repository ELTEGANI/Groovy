package com.example.learntesting.playlist

import com.example.learntesting.R
import javax.inject.Inject



class PlayListMapper @Inject constructor(): Function1<List<PlayListRaw>,List<PlayList>>{
    override fun invoke(playListRaw: List<PlayListRaw>): List<PlayList> {
        return playListRaw.map {
            val image = when(it.category) {
                "rock"-> R.drawable.rock
                else -> R.drawable.ic_play
            }
            PlayList(it.id,it.name,it.category,image)
        }
    }

}
