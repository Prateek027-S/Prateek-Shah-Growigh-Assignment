package com.prateekshah.prateekshahgrowghassignment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.navigation.fragment.findNavController


/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Handler(Looper.getMainLooper()).postDelayed({
            if (onBoardingIsFinished()) {
                findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment)
            }
        }, 2000)

        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        val animBottom = AnimationUtils.loadAnimation(view.context, androidx.transition.R.anim.abc_grow_fade_in_from_bottom)
        val imgSplash = view.findViewById<ImageView>(R.id.splash_img)
        imgSplash.animation = animBottom

        return view
    }

    private fun onBoardingIsFinished(): Boolean {
        val sharedPreferences = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("finished", false)
    }
}