package com.example.presentation.ui.analysis.datedetail

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.databinding.ItemInterviewVideoBinding
import com.example.presentation.model.analysis.InterviewVideo
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class DateDetailActionListAdapter(val context: Context, val activity: Activity) :
    ListAdapter<InterviewVideo, RecyclerView.ViewHolder>(diffUtil) {

    inner class DateDetailActionViewHolder(
        private val binding: ItemInterviewVideoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: InterviewVideo) {
            binding.tvQuestion.text = item.question
            setExo(item.url)
        }

        private fun setExo(url: String) {
            val player = ExoPlayer.Builder(context).build()
            binding.playerView.player = player
            val uri = Uri.parse(url)
            val mediaItem = MediaItem.fromUri(uri)
            player.setMediaItem(mediaItem)

            // player.playWhenReady = true
            player.prepare()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DateDetailActionViewHolder(
            ItemInterviewVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DateDetailActionViewHolder).bind(getItem(position) ?: return)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<InterviewVideo>() {
            override fun areItemsTheSame(
                oldItem: InterviewVideo,
                newItem: InterviewVideo
            ): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(
                oldItem: InterviewVideo,
                newItem: InterviewVideo
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}