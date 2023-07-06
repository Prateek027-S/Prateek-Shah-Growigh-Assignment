package com.prateekshah.prateekshahgrowghassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.prateekshah.prateekshahgrowghassignment.databinding.FragmentFeedsBinding


/**
 * A simple [Fragment] subclass.
 * Use the [FeedsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedsFragment : Fragment() {

    private val feedsViewModel: FeedsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentFeedsBinding.inflate(inflater)

        binding?.apply {
            lifecycleOwner = this@FeedsFragment
            viewModel = feedsViewModel
        }
        binding.photosGrid.adapter = PhotoGridAdapter()
        binding.swipeRefreshLayout.setOnRefreshListener {
            feedsViewModel.getTenPhotos()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        return binding.root
    }
}