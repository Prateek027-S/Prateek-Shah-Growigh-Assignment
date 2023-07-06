package com.prateekshah.prateekshahgrowghassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.prateekshah.prateekshahgrowghassignment.databinding.FragmentUploadImageBinding


/**
 * A simple [Fragment] subclass.
 * Use the [UploadImageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UploadImageFragment : Fragment() {

    private var binding: FragmentUploadImageBinding? = null
    private lateinit var selectedImgView: ImageView
    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()) {
        selectedImgView.setImageURI(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentUploadImageBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.fragment = this
        selectedImgView = binding!!.selectedImg
    }

    fun selectImage() {
        contract.launch("image/*")
    }

    fun goToWelcomeFragment() {
        findNavController().navigate(R.id.action_uploadImageFragment_to_welcomeFragment)
    }
}