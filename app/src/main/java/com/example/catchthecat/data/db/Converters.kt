package com.example.catchthecat.data.db

import androidx.room.TypeConverter
import com.example.catchthecat.data.model.Image
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {
    @TypeConverter
    fun fromImagesList(images: List<Image>?): String? {
        return images?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun toImagesList(imagesString: String?): List<Image>? {
        return imagesString?.let { Gson().fromJson(it, object : TypeToken<List<Image>>() {}.type) }
    }
}
