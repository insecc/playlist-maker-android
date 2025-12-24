package com.practicum.playlistmaker.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.practicum.playlistmaker.data.dao.PlaylistDao
import com.practicum.playlistmaker.data.dao.TrackDao
import com.practicum.playlistmaker.data.PlaylistEntity
import com.practicum.playlistmaker.data.PlaylistTrackCR
import com.practicum.playlistmaker.data.TrackEntity

@Database(
    version = 1,
    entities = [
        TrackEntity::class,
        PlaylistEntity::class,
        PlaylistTrackCR::class
    ],
    exportSchema = true
)
abstract class MainAppDb : RoomDatabase() {
    abstract fun playlistDao(): PlaylistDao
    abstract fun trackDao(): TrackDao

    companion object {
        @Volatile
        private var INSTANCE: com.practicum.playlistmaker.data.db.MainAppDb? = null

        fun getDatabase(context: Context): com.practicum.playlistmaker.data.db.MainAppDb {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MainAppDb::class.java,
                    "playlistmaker.db"
                )
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}