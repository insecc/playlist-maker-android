package com.practicum.playlistmaker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.practicum.playlistmaker.data.TrackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert_Track(track: TrackEntity)

    @Update
    suspend fun update_Track(track: TrackEntity)

    @Delete
    suspend fun delete_Track(track: TrackEntity)

    @Query("SELECT * FROM tracks WHERE isFavorite = 1")
    fun get_Favorite(): Flow<List<TrackEntity>>

    @Query("SELECT CAST(COUNT(*) AS LONG) FROM playlist_track_cross_ref WHERE trackId = :trackId")
    suspend fun countPLST_For_Track(trackId: Long): Long

    @Query("SELECT * FROM tracks WHERE trackId = :trackId")
    fun getTrackID(trackId: Long): Flow<TrackEntity?>
}