package com.prateekshah.prateekshahgrowghassignment

import android.content.Intent
import android.provider.Settings.System.getString
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prateekshah.prateekshahgrowghassignment.databinding.GridViewItemBinding
import com.prateekshah.prateekshahgrowghassignment.network.ImageResponse

class PhotoGridAdapter(private val onShareButtonClickListener: OnShareButtonClickListener): ListAdapter<ImageResponse, PhotoGridAdapter.FeedsPhotoViewHolder>(DiffCallback) {

    class FeedsPhotoViewHolder(private var binding:
                              GridViewItemBinding
    ):
        RecyclerView.ViewHolder(binding.root) {
        val likeBtn = binding.likeBtn
        val numLikes = binding.numLikes
        val shareBtn = binding.shareBtn
        fun bind(imageResponse: ImageResponse, holder: FeedsPhotoViewHolder){
            binding.photo = imageResponse
            binding.numLikes.text = holder.itemView.context.getString(R.string.num_likes, 0)
            binding.numComments.text = holder.itemView.context.getString(R.string.num_comments, 0)
            binding.executePendingBindings()
        }
    }

    interface OnShareButtonClickListener {
        fun onShareButtonClick(imageUrl: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridAdapter.FeedsPhotoViewHolder {
        return FeedsPhotoViewHolder(GridViewItemBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.FeedsPhotoViewHolder, position: Int) {
        val imageResponse = getItem(position)
        holder.likeBtn.setOnClickListener {
            if (holder.likeBtn.isSelected) {
                holder.likeBtn.isSelected = false
                holder.numLikes.text = holder.itemView.context.getString(R.string.num_likes, 0)
            }
            else {
                holder.likeBtn.isSelected = false
                holder.numLikes.text = holder.itemView.context.getString(R.string.num_likes, 1)
            }
        }
        holder.shareBtn.setOnClickListener {
            val imageUrl = imageResponse.downloadUrl
            onShareButtonClickListener.onShareButtonClick(imageUrl)
        }
        holder.bind(imageResponse, holder)
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