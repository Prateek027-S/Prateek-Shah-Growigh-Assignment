package com.prateekshah.prateekshahgrowghassignment

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.prateekshah.prateekshahgrowghassignment.network.ImageResponse

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) { // load(){} from Coil, to load the image from the imgUri object into the imgView.
            placeholder(R.drawable.loading_animation) //sets the placeholder loading image to use while loading (the loading_animation drawable).
            error(R.drawable.ic_broken_image) // sets an image to use if image loading fails (the broken_image drawable).
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ImageResponse>?){
    val adapter = recyclerView.adapter as PhotoGridAdapter // cast recyclerView.adapter to PhotoGridAdapter
    adapter.submitList(data) //Submits a new list to be diffed, and displayed.
}

@BindingAdapter("imageApiStatus")
fun bindStatus(statusImageView: ImageView, status: ImageApiStatus?){
    // When the app is in the loading state or the error state, the ImageView should be visible. When the app is done loading, the ImageView should be invisible.
    when(status){
        ImageApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ImageApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ImageApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }

        else -> {}
    }
}