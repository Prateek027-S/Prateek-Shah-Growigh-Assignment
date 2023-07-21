package com.prateekshah.prateekshahgrowghassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prateekshah.prateekshahgrowghassignment.data.VideoDataSource
import com.prateekshah.prateekshahgrowghassignment.videosAdapter.VideosAdapter


/**
 * A simple [Fragment] subclass.
 * Use the [VideosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VideosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_videos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myDataset = VideoDataSource().loadVideoIds() //object created and method called simultaneously
        val recyclerView = view.findViewById<RecyclerView>(R.id.videos_recycler_view) // Reference to RecyclerView
        recyclerView.adapter = VideosAdapter(myDataset)

        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true)
    }
}