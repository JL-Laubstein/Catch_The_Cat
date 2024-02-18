package com.example.catchthecat.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.catchthecat.R
import com.example.catchthecat.data.model.ImageData
import com.example.catchthecat.databinding.ImageFeedItemBinding

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
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageFeedViewHolder, position: Int) {
        holder.bind(getItem(position), imageListener)
    }

    inner class ImageFeedViewHolder(private val binding: ImageFeedItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageData: ImageData, listener: ImageListener) {
            binding.imageData = imageData
            binding.listener = listener

            binding.ivFeedImage.scaleType =
                if (imageData.images.isNullOrEmpty()) ImageView.ScaleType.CENTER_INSIDE else ImageView.ScaleType.CENTER_CROP

            Glide.with(binding.root)
                .load(if (imageData.images.isNullOrEmpty()) R.drawable.baseline_error_24 else imageData.images[0].link)
                .into(binding.ivFeedImage)
        }
    }
}