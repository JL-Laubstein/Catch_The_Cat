package com.example.catchthecat.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catchthecat.R
import com.example.catchthecat.data.model.ImageData
import com.example.catchthecat.databinding.ImageFeedItemBinding
import com.facebook.shimmer.ShimmerFrameLayout

class ImageFeedAdapter(private val imageListener: ImageListener) :
    ListAdapter<ImageData, ImageFeedAdapter.ImageFeedViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ImageData>() {
            override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageFeedViewHolder {
        return ImageFeedViewHolder(
            ImageFeedItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageFeedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ImageFeedViewHolder(binding: ImageFeedItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val shimmerLayout: ShimmerFrameLayout = binding.shimmerLayout
        private val feedImage = binding.ivFeedImage
        private val bindingRoot = binding.root

        fun bind(imageData: ImageData) {
            Glide.with(bindingRoot)
                .load(R.drawable.baseline_image_24)
                .into(feedImage)

            shimmerLayout.stopShimmer()

            Glide.with(bindingRoot)
                .load(if (imageData.images.isNullOrEmpty()) R.drawable.baseline_error_24 else imageData.images[0].link)
                .into(feedImage)

            Log.i("JAO", "Image URL: ${if (imageData.images.isNullOrEmpty()) "Error" else imageData.images[0].link}")
            Log.i("JAO", "Image Title: ${imageData.title}")
        }
    }
}