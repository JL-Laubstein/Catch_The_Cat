package com.example.catchthecat.data.model

data class GetImageData(
    val data: List<ImageData>,
    val success: Boolean,
    val status: Int
)