package com.practicum.playlistmaker.data.network

import com.practicum.playlistmaker.data.dto.ResponseTrack
import com.practicum.playlistmaker.data.dto.SearchRequestTrack
import retrofit2.HttpException
import java.io.IOException

class RetrofitNtClient(
    private val api: ApiService
) : NtClient {
    override suspend fun doRequest(dto: Any): ResponseTrack {
        if (dto !is SearchRequestTrack) {
            return ResponseTrack().apply { resultCode = 400 }
        }
        return try {
            api.searchTracks(dto.expression).apply { resultCode = 200 }
        } catch (e: Throwable) {
            ResponseTrack().apply {
                resultCode = when (e) {
                    is IOException -> -1
                    is HttpException -> e.code()
                    else -> 500
                }
            }
        }
    }
}