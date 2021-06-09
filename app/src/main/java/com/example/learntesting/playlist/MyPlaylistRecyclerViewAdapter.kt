package com.example.learntesting.playlist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.learntesting.R
import com.example.learntesting.databinding.PlaylistItemBinding


class MyPlaylistRecyclerViewAdapter(
    private val values: List<PlayList>,
    private val listener:(String) -> Unit
) : RecyclerView.Adapter<MyPlaylistRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            PlaylistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.playListName.text = item.name
        holder.playListCategory.text = item.category
        holder.playListImage.setImageResource(item.imageId)
        holder.root.setOnClickListener {
            listener(item.id)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: PlaylistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val playListName: TextView = binding.playlistName
        val playListCategory: TextView = binding.playlistCategory
        val playListImage: ImageView = binding.imageId
        val root:View = binding.playlistItemRoot
    }

}