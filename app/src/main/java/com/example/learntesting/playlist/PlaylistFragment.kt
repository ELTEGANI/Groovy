package com.example.learntesting.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learntesting.R


class PlaylistFragment : Fragment() {

    lateinit var viewModel: PlayListViewModel
    lateinit var viewModelFactory: PlayListViewModelFactory
     private val repositry = PlayListRepository()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        setUpViewModel()

        viewModel.playList.observe(this as LifecycleOwner) { playlists ->
            with(view as RecyclerView) {
                if(playlists.getOrNull() != null)
                 setUpList(playlists.getOrNull()!!)
                else{

                }
            }
        }

        return view
    }

    private fun RecyclerView.setUpList(playlists: List<PlayList>) {
        layoutManager = LinearLayoutManager(context)
        adapter = MyPlaylistRecyclerViewAdapter(playlists)
    }

    private fun setUpViewModel() {
        viewModelFactory = PlayListViewModelFactory(repositry)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlayListViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlaylistFragment().apply { }
    }
}