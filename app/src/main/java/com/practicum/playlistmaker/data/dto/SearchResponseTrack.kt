package com.practicum.playlistmaker.data.dto

data class SearchResponseTrack(
    val count: Int,
    val results: List<TrackDto>
) : ResponseTrack()