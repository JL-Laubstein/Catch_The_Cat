package com.example.catchthecat.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.catchthecat.R
import java.util.*

@BindingAdapter("bind:image")
fun loadImage(imageView: ImageView, imageUrl: String?) {
    if (imageUrl != null) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .into(imageView)
    } else {
        Glide.with(imageView.context)
            .load(R.drawable.baseline_error_24)
            .into(imageView)
    }
}