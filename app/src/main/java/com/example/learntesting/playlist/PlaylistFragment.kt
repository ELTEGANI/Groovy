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
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PlaylistFragment : Fragment() {

    lateinit var viewModel: PlayListViewModel
    lateinit var viewModelFactory: PlayListViewModelFactory

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.122.1:3000/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api = retrofit.create(PlayListApi::class.java)
    private var playListServices = PlayListServices(api)
    private val repositroy = PlayListRepository(playListServices)

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
        viewModelFactory = PlayListViewModelFactory(repositroy)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlayListViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlaylistFragment().apply { }
    }
}