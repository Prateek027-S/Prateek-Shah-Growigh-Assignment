package com.prateekshah.prateekshahgrowghassignment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prateekshah.prateekshahgrowghassignment.databinding.GridViewItemBinding
import com.prateekshah.prateekshahgrowghassignment.network.ImageResponse

class PhotoGridAdapter: ListAdapter<ImageResponse, PhotoGridAdapter.FeedsPhotoViewHolder>(DiffCallback) {

    class FeedsPhotoViewHolder(private var binding:
                              GridViewItemBinding
    ):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageResponse: ImageResponse){
            binding.photo = imageResponse
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridAdapter.FeedsPhotoViewHolder {
        return FeedsPhotoViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.FeedsPhotoViewHolder, position: Int) {
        val imageResponse = getItem(position)
        holder.bind(imageResponse)
    }

    companion object DiffCallback: DiffUtil.ItemCallback<ImageResponse>(){
        override fun areItemsTheSame(oldItem: ImageResponse, newItem: ImageResponse): Boolean { //whether 2 objects(MarsPhoto) are same
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageResponse, newItem: ImageResponse): Boolean { // whether data of both objects are same
            return oldItem.downloadUrl == newItem.downloadUrl
        }
    }
}