package com.practicum.playlistmaker.data.network

import com.practicum.playlistmaker.data.dto.ResponseTrack

interface NtClient {
    suspend fun doRequest(dto: Any): ResponseTrack
}