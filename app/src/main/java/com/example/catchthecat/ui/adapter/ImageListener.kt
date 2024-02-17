package com.example.catchthecat.ui.adapter

import com.example.catchthecat.data.model.ImageData

class ImageListener(
    val clickListener: (receipts: ImageData) -> Unit
) {
    fun onClick(imageData: ImageData) {
        clickListener(imageData)
    }
}