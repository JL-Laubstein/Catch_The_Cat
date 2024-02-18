package com.example.catchthecat.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.catchthecat.data.model.ImageData
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageFeedDao {
    @Query("SELECT * FROM image_data")
    fun getAll(): Flow<List<ImageData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg imageData: ImageData)

    @Query("DELETE FROM image_data")
    suspend fun deleteTokens()
}