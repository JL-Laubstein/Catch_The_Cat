package com.example.catchthecat.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.catchthecat.data.db.dao.ImageFeedDao
import com.example.catchthecat.data.model.ImageData


@Database(
    version = 1,
    entities = [ImageData::class],
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun imageFeedDao(): ImageFeedDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "catchthecat.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}