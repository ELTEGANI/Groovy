package com.example.learntesting.detailes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.learntesting.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_play_list_detail.*
import javax.inject.Inject

@AndroidEntryPoint
class PlayListDetailsFragment : Fragment() {

    @Inject
    lateinit var playListDetailFragmentViewModel : PlayListDetailFragmentViewModel

    @Inject
    lateinit var viewModelFactory: PlayListDetailFragmentViewModelFactory

    private val playListDetailsFragmentArgs : PlayListDetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_play_list_detail, container, false)
        val id = playListDetailsFragmentArgs.playlistId
        setUpViewModel()
        playListDetailFragmentViewModel.getPlayListDetails(id)
        observeLiveData()
        return  view
    }

    private fun observeLiveData() {
        playListDetailFragmentViewModel.playListDetails.observe(this as LifecycleOwner) { playListDetails ->
            if (playListDetails.getOrNull() != null) {
                setUi(playListDetails)
            } else {

            }
        }
    }

    private fun setUpViewModel() {
        playListDetailFragmentViewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(playListDetailFragmentViewModel::class.java)
    }

    private fun setUi(playListDetails: Result<PlayListDetail>) {
        playlist_detail.text = playListDetails.getOrNull()!!.name
        playlist_detail.text = playListDetails.getOrNull()!!.details
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlayListDetailsFragment()
    }
}