package com.practicum.playlistmaker.data.network

import com.practicum.playlistmaker.data.dto.SearchResponseTrack
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/search?entity=song")
    suspend fun searchTracks(@Query("term") term: String): SearchResponseTrack
}