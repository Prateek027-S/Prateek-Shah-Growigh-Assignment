package com.prateekshah.prateekshahgrowghassignment.videosAdapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.prateekshah.prateekshahgrowghassignment.R
import com.prateekshah.prateekshahgrowghassignment.data.VideoModel
import com.yausername.youtubedl_android.YoutubeDL
import com.yausername.youtubedl_android.YoutubeDLException
import com.yausername.youtubedl_android.YoutubeDLRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class VideosAdapter(
    private val dataset: List<VideoModel>
): RecyclerView.Adapter<VideosAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        private var videoJob: Job? = null
        val videoView: VideoView = view.findViewById(R.id.videoView)
        val progressBar: ProgressBar = view.findViewById(R.id.videoProgressBar)
        val videoTitle: TextView = view.findViewById(R.id.video_title)
        val profileName: TextView = view.findViewById(R.id.video_profile_name)
        var isVideoPlaying = false

        fun setData(obj:VideoModel) {
            videoJob?.cancel()
            progressBar.visibility = View.VISIBLE


            videoJob = CoroutineScope(IO).launch {
                try {
                    val request = YoutubeDLRequest(obj.videoUrl)
                    request.addOption("-f", "best")
                    val streamInfo = YoutubeDL.getInstance().getInfo(request)
                    withContext(Dispatchers.Main) {
                        setupVideoView(streamInfo.url)
                        progressBar.visibility = View.GONE
                        //makeItemVisible()

                        videoTitle.text = streamInfo.title
                        profileName.text = streamInfo.uploader
                        Log.d("Download", streamInfo.url)
                    }
                    /*val videoUri = Uri.parse(obj.videoUrl)
                    videoView.setVideoURI(videoUri)
                    videoView.setOnPreparedListener { mediaPlayer ->
                        progressBar.visibility = View.GONE
                        mediaPlayer.start()
                    }

                    videoView.setOnCompletionListener { mediaPlayer ->
                        mediaPlayer.start()
                    }*/
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        progressBar.visibility = View.GONE
                        Log.d("Download Error", e.message.toString())
                    }
                }
            }
        }

        fun setupVideoView(videoUrl: String){
            videoView.setOnPreparedListener {
                progressBar.visibility = View.GONE
                videoView.start()
                it.isLooping = true
                // Add OnClickListener to toggle play and pause
                videoView.setOnClickListener {
                    if (isVideoPlaying) {
                        videoView.pause()
                    } else {
                        videoView.start()
                    }
                    isVideoPlaying = !isVideoPlaying
                }
            }
            videoView.setVideoURI(Uri.parse(videoUrl))
        }
        fun cancelVideoJob() {
            videoJob?.cancel()
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

    override fun onViewDetachedFromWindow(holder: ItemViewHolder) {
        holder.cancelVideoJob()
    }

    //return size of the dataset
    override fun getItemCount(): Int {
        return dataset.size
    }
}