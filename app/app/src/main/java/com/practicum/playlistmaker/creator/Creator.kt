package com.practicum.playlistmaker.creator

import android.content.Context
import com.practicum.playlistmaker.data.db.MainAppDb
import com.practicum.playlistmaker.data.network.ApiService
import com.practicum.playlistmaker.data.network.RetrofitNtClient
import com.practicum.playlistmaker.data.preferences.SearchHistoryPreferences
import com.practicum.playlistmaker.data.preferences.dataStore
import com.practicum.playlistmaker.data.repository.PlaylistsRepositoryImpl
import com.practicum.playlistmaker.data.repository.SearchHistoryRepositoryImpl
import com.practicum.playlistmaker.data.repository.TracksLocalRepositoryImpl
import com.practicum.playlistmaker.data.repository.TracksRepositoryImpl
import com.practicum.playlistmaker.domain.PlaylistsRepository
import com.practicum.playlistmaker.domain.SearchHistoryRepository
import com.practicum.playlistmaker.domain.TracksLocalRepository
import com.practicum.playlistmaker.domain.TracksRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Creator {
    private const val BASE_URL = "https://itunes.apple.com"
    private val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    fun AppDbProv(context: Context): MainAppDb {
        return MainAppDb.getDatabase(context)
    }
    fun TracksRepositoryProv(): TracksRepository {
        val networkClient = RetrofitNtClient(apiService)
        return TracksRepositoryImpl(networkClient)
    }

    fun PlaylistsRepositoryProv(context: Context): PlaylistsRepository {
        val db = AppDbProv(context)
        return PlaylistsRepositoryImpl(db.playlistDao(), db.trackDao())
    }

    fun SearchHistoryRepositoryProv(context: Context): SearchHistoryRepository {
        val preferences = SearchHistoryPreferences(context.dataStore)
        return SearchHistoryRepositoryImpl(preferences)
    }

    fun TracksLocalRepositoryProv(context: Context): TracksLocalRepository {
        val db = AppDbProv(context)
        return TracksLocalRepositoryImpl(db.trackDao(), db.playlistDao())
    }
}