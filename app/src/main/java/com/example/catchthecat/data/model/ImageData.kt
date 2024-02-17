package com.example.catchthecat.data.model

import androidx.room.Entity
@Entity
data class ImageData(
    val id: String,
    val title: String?,
    val description: String?,
    val datetime: Long,
    val cover: String,
    val cover_width: Int,
    val cover_height: Int,
    val account_url: String?,
    val account_id: Long?,
    val privacy: String,
    val layout: String,
    val views: Int,
    val link: String,
    val ups: Int,
    val downs: Int,
    val points: Int,
    val score: Int,
    val is_album: Boolean,
    val comment_count: Int,
    val favorite_count: Int,
    val images_count: Int,
    val images: List<Image>?
)