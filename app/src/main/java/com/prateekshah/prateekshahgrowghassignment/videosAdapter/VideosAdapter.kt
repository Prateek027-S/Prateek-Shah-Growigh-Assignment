package com.prateekshah.prateekshahgrowghassignment.videosAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.prateekshah.prateekshahgrowghassignment.R

class VideosAdapter(
    private val dataset: List<String>
): RecyclerView.Adapter<VideosAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val youTubePlayerView: YouTubePlayerView = view.findViewById(R.id.youtube_player_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    //called by layout manager to replace the contents of a list item view
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(item, 0f)
            }
        })
    }

    //return size of the dataset
    override fun getItemCount(): Int {
        return dataset.size
    }
}