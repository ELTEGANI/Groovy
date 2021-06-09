package com.example.learntesting.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learntesting.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_playlist.*
import kotlinx.android.synthetic.main.fragment_playlist.view.*
import javax.inject.Inject


@AndroidEntryPoint
class PlaylistFragment : Fragment() {

    lateinit var viewModel: PlayListViewModel

    @Inject
    lateinit var viewModelFactory: PlayListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)

        setUpViewModel()

        viewModel.loader.observe(this as LifecycleOwner) { Loader ->
           when(Loader){
               true-> loader.visibility = View.VISIBLE
               else-> loader.visibility = View.GONE
           }
        }

        viewModel.playList.observe(this as LifecycleOwner) { playlists ->
                if(playlists.getOrNull() != null)
                 setUpList(view.playlist,playlists.getOrNull()!!)
                else{
            }
        }

        return view
    }

    private fun setUpList(recyclerView: RecyclerView, playlists: List<PlayList>) {
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyPlaylistRecyclerViewAdapter(playlists) { id ->
                val playlistFragmentDirections =
                    PlaylistFragmentDirections.actionPlaylistFragmentToPlayListDetailFragment(id)
                findNavController().navigate(playlistFragmentDirections)
            }
        }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlayListViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlaylistFragment().apply { }
    }
}