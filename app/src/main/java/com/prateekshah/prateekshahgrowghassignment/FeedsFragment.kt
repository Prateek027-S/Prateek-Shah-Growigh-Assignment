package com.prateekshah.prateekshahgrowghassignment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.prateekshah.prateekshahgrowghassignment.databinding.FragmentFeedsBinding


/**
 * A simple [Fragment] subclass.
 * Use the [FeedsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedsFragment : Fragment(), PhotoGridAdapter.OnShareButtonClickListener {

    private val feedsViewModel: FeedsViewModel by viewModels()
    private lateinit var binding: FragmentFeedsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentFeedsBinding.inflate(inflater)

        binding?.apply {
            lifecycleOwner = this@FeedsFragment
            viewModel = feedsViewModel
            fragment = this@FeedsFragment
        }
        binding.photosGrid.adapter = PhotoGridAdapter(this)
        binding.swipeRefreshLayout.setOnRefreshListener {
            feedsViewModel.getTenPhotos()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationBar = binding.bottomNavigationView
        bottomNavigationBar.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    if(bottomNavigationBar.visibility == View.VISIBLE) {
                        bottomNavigationBar.visibility = View.GONE
                    }
                    if(binding.uploadImgFab.visibility == View.VISIBLE)
                        binding.uploadImgFab.visibility = View.GONE
                    replaceFragment(FeedsFragment()) }
                R.id.videos -> {
                    binding.uploadImgFab.visibility = View.GONE
                    replaceFragment(VideosFragment())
                }
                R.id.map -> {
                    binding.uploadImgFab.visibility = View.GONE
                    bottomNavigationBar.visibility = View.GONE
                    replaceFragment(MapFragment())
                }
                else -> {}
            }
            true
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }

    fun goToUploadImageFragment() {
        findNavController().navigate(R.id.action_feedsFragment_to_uploadImageFragment)
    }

    override fun onShareButtonClick(imageUrl: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, imageUrl)
        startActivity(Intent.createChooser(shareIntent, "Share Post Image URL"))
    }
}