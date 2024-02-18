package com.example.catchthecat.data.model

data class Image(
    val id: String?,
    val title: String?,
    val description: String?,
    val datetime: Long?,
    val type: String?,
    val animated: Boolean?,
    val width: Int?,
    val height: Int?,
    val size: Int?,
    val views: Int?,
    val bandwidth: Long?,
    val favorite: Boolean?,
    val account_url: String?,
    val account_id: Long?,
    val has_sound: Boolean?,
    val edited: String?,
    val link: String?,
    val comment_count: Int?,
    val favorite_count: Int?,
    val ups: Int?,
    val downs: Int?,
    val points: Int?,
    val score: Int?
)
