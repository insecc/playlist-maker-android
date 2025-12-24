package com.practicum.playlistmaker.data.repository

import com.practicum.playlistmaker.data.dao.PlaylistDao
import com.practicum.playlistmaker.data.dao.TrackDao
import com.practicum.playlistmaker.data.PlaylistTrackCR
import com.practicum.playlistmaker.data.TrackEntity
import com.practicum.playlistmaker.domain.Track
import com.practicum.playlistmaker.domain.TracksLocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class TracksLocalRepositoryImpl(
    private val trackDao: TrackDao,
    private val playlistDao: PlaylistDao
) : TracksLocalRepository {
    override suspend fun insertTrackToPlaylist(track: Track, playlistId: Long) = withContext(Dispatchers.IO) {
        var entity = trackDao.getTrackID(track.trackId).firstOrNull()
        if (entity == null) {
            entity = TrackEntity(
                trackId = track.trackId,
                trackName = track.trackName,
                artistName = track.artistName,
                trackTimeMillis = track.trackTimeMillis,
                artworkUrl100 = track.artworkUrl100,
                previewUrl = track.previewUrl,
                isFavorite = track.favorite
            )
            trackDao.insert_Track(entity)
        }
        playlistDao.insertCR(PlaylistTrackCR(playlistId, track.trackId))
    }

    override suspend fun deleteTrackFromPlaylist(trackId: Long, playlistId: Long) = withContext(Dispatchers.IO) {
        playlistDao.deleteSpecificCR(playlistId, trackId)
        val count = trackDao.countPLST_For_Track(trackId)
        val entity = trackDao.getTrackID(trackId).firstOrNull()
        if (entity != null && !entity.isFavorite && count == 0L) {
            trackDao.delete_Track(entity)
        }
    }

    override suspend fun updateTrackFavoriteStatus(track: Track, isFavorite: Boolean) = withContext(Dispatchers.IO) {
        var existing = trackDao.getTrackID(track.trackId).firstOrNull()
        if (existing == null) {
            if (isFavorite) {
                existing = TrackEntity(
                    trackId = track.trackId,
                    trackName = track.trackName,
                    artistName = track.artistName,
                    trackTimeMillis = track.trackTimeMillis,
                    artworkUrl100 = track.artworkUrl100,
                    previewUrl = track.previewUrl,
                    isFavorite = isFavorite
                )
                trackDao.insert_Track(existing)
            }
        } else {
            val updated = existing.copy(isFavorite = isFavorite)
            trackDao.update_Track(updated)
            if (!isFavorite) {
                val count = trackDao.countPLST_For_Track(track.trackId)
                if (count == 0L) {
                    trackDao.delete_Track(updated)
                }
            }
        }
    }

    override fun getFavoriteTracks(): Flow<List<Track>> = trackDao.get_Favorite().map { entities ->
        entities.map {
            Track(
                trackId = it.trackId,
                trackName = it.trackName,
                artistName = it.artistName,
                trackTimeMillis = it.trackTimeMillis,
                artworkUrl100 = it.artworkUrl100,
                previewUrl = it.previewUrl,
                favorite = it.isFavorite
            )
        }
    }

    override fun getTrackById(trackId: Long): Flow<Track?> = trackDao.getTrackID(trackId).map { entity ->
        entity?.let {
            Track(
                trackId = it.trackId,
                trackName = it.trackName,
                artistName = it.artistName,
                trackTimeMillis = it.trackTimeMillis,
                artworkUrl100 = it.artworkUrl100,
                previewUrl = it.previewUrl,
                favorite = it.isFavorite
            )
        }
    }
}