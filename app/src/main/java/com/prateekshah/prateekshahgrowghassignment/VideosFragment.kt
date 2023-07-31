package com.prateekshah.prateekshahgrowghassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
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
        val viewPager2 = view.findViewById<ViewPager2>(R.id.viewPager2) // Reference to RecyclerView
        viewPager2.adapter = VideosAdapter(myDataset)
    }
}