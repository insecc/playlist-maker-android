package com.practicum.playlistmaker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.practicum.playlistmaker.data.PlaylistEntity
import com.practicum.playlistmaker.data.PlaylistTrackCR
import com.practicum.playlistmaker.data.TrackEntity
import kotlinx.coroutines.flow.Flow

data class PlaylistWithTracks(
    @androidx.room.Embedded val playlist: PlaylistEntity,
    @androidx.room.Relation(
        parentColumn = "id",
        entityColumn = "trackId",
        associateBy = androidx.room.Junction(
            value = PlaylistTrackCR::class,
            parentColumn = "playlistId",
            entityColumn = "trackId"
        )
    )
    val tracks: List<TrackEntity>
)

@Dao
interface PlaylistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPls(playlist: PlaylistEntity)

    @Delete
    suspend fun deletePlst(playlist: PlaylistEntity)

    @Transaction
    @Query("SELECT * FROM playlists")
    fun getAll(): Flow<List<PlaylistWithTracks>>

    @Transaction
    @Query("SELECT * FROM playlists WHERE id = :playlistId")
    fun getPlst(playlistId: Long): Flow<PlaylistWithTracks?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCR(crossRef: PlaylistTrackCR)

    @Query("DELETE FROM playlist_track_cross_ref WHERE playlistId = :id")
    suspend fun deleteCR_plst(id: Long)

    @Query("DELETE FROM playlist_track_cross_ref WHERE playlistId = :playlistId AND trackId = :trackId")
    suspend fun deleteSpecificCR(playlistId: Long, trackId: Long)


}