package com.example.catchthecat.ui.adapter

import com.example.catchthecat.data.model.ImageData

class ImageListener(
    val clickListener: (imageData: ImageData?) -> Unit
) {
    fun onClick(imageData: ImageData?) {
        clickListener(imageData)
    }
}