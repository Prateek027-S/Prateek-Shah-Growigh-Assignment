package com.prateekshah.prateekshahgrowghassignment.onboardscreens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.prateekshah.prateekshahgrowghassignment.R
import com.prateekshah.prateekshahgrowghassignment.databinding.FragmentOurVisionBinding


/**
 * A simple [Fragment] subclass.
 * Use the [OurVisionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OurVisionFragment : Fragment() {

    private var binding: FragmentOurVisionBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentOurVisionBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            fragment = this@OurVisionFragment
        }
    }

    fun goToWelcomeFragment() {
        findNavController().navigate(R.id.action_onBoardingFragment_to_welcomeFragment)
        onBoardingIsFinished()
    }

    private fun onBoardingIsFinished() {
        val sharedPreferences = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("finished", true)
        editor.apply()
    }
}