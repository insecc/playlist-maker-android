package com.practicum.playlistmaker.data

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "playlist_track_cross_ref",
    primaryKeys = ["playlistId", "trackId"],
    indices = [Index(value = ["trackId"])]
)

data class PlaylistTrackCR(
    val playlistId: Long,
    val trackId: Long
)