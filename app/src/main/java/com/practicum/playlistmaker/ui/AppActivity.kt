package com.practicum.playlistmaker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.practicum.playlistmaker.creator.Creator
import com.practicum.playlistmaker.ui.navigation.PlaylistNavHost
import com.practicum.playlistmaker.ui.theme.PlaylistMakerTheme

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDark = remember { mutableStateOf(false) }

            val playlistsRepo = remember {
                Creator.PlaylistsRepositoryProv(applicationContext)
            }

            val tracksLocalRepo = remember {
                Creator.TracksLocalRepositoryProv(applicationContext)
            }

            val searchRepository = remember { Creator.TracksRepositoryProv() }

            val searchHistoryRepo =
                remember { Creator.SearchHistoryRepositoryProv(applicationContext) }

            PlaylistMakerTheme(darkTheme = isDark.value) {
                Surface(
                    modifier = Modifier.Companion.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlaylistNavHost(
                        searchRepository = searchRepository,
                        playlistsRepository = playlistsRepo,
                        tracksLocalRepository = tracksLocalRepo,
                        searchHistoryRepository = searchHistoryRepo,
                        isDarkTheme = isDark.value,
                        onToggleTheme = { isDark.value = it }
                    )
                }
            }
        }
    }
}