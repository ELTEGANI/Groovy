package com.example.learntesting.detailes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.learntesting.R
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_play_list_detail.*
import kotlinx.android.synthetic.main.fragment_playlist.*
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
        observePlayListDetail()
        displayLoader()
        return  view
    }

    private fun displayLoader() {
        playListDetailFragmentViewModel.loader.observe(this as LifecycleOwner) { Loader ->
            when (Loader) {
                true -> details_loader.visibility = View.VISIBLE
                else -> details_loader.visibility = View.GONE
            }
        }
    }

    @SuppressLint("ShowToast")
    private fun observePlayListDetail() {
        playListDetailFragmentViewModel.playListDetails.observe(this as LifecycleOwner) { playListDetails ->
            if (playListDetails.getOrNull() != null) {
                setUi(playListDetails)
            } else {
               Snackbar.make(
                   playlist_detail_root,
                   getString(R.string.generic_error),
                   Snackbar.LENGTH_LONG
               ).show()
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