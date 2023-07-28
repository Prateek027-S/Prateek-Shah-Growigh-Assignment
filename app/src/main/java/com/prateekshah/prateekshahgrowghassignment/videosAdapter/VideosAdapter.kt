package com.prateekshah.prateekshahgrowghassignment.videosAdapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.prateekshah.prateekshahgrowghassignment.R
import com.prateekshah.prateekshahgrowghassignment.data.VideoModel

class VideosAdapter(
    private val dataset: List<VideoModel>
): RecyclerView.Adapter<VideosAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        //val youTubePlayerView: YouTubePlayerView = view.findViewById(R.id.youtube_player_view)
        val videoView: VideoView = view.findViewById(R.id.videoView)
        val title: TextView = view.findViewById(R.id.textVideoTitle)
        val desc: TextView = view.findViewById(R.id.textVideoDescription)
        val progressBar: ProgressBar = view.findViewById(R.id.videoProgressBar)

        fun setData(obj:VideoModel) {
            val videoUri = Uri.parse(obj.videoUrl)
            videoView.setVideoURI(videoUri)
            title.text = obj.title
            desc.text = obj.desc

            videoView.setOnPreparedListener { mediaPlayer ->
                progressBar.visibility = View.GONE
                mediaPlayer.start()
            }

            videoView.setOnCompletionListener { mediaPlayer ->
                mediaPlayer.start()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    //called by layout manager to replace the contents of a list item view
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.setData(item)
    }

    //return size of the dataset
    override fun getItemCount(): Int {
        return dataset.size
    }
}