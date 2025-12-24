package com.practicum.playlistmaker.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicum.playlistmaker.R
import com.practicum.playlistmaker.presentation.AppTrack
import com.practicum.playlistmaker.ui.componentsRow.TrackRow
import com.practicum.playlistmaker.ui.theme.YS
import androidx.compose.ui.graphics.Color
import com.practicum.playlistmaker.presentation.toAppTrack
import com.practicum.playlistmaker.ui.viewModel.FavoritesViewModel


@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel,
    onBackClick: () -> Unit,
    onTrackClick: (AppTrack) -> Unit,
    isDarkTheme: Boolean
) {
    val favoriteList by viewModel.favoriteList.collectAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.loadFavorites()
    }

    val backgroundColor = if (isDarkTheme) Color(0xFF1A1B22) else Color.White
    val textColor = if (isDarkTheme) Color.White else Color(0xFF1A1B22)
    Color(0xFFAEAFB4)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(start = 16.dp)
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(R.drawable.arrowleft),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = textColor
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = stringResource(R.string.favorites),
                    fontFamily = YS,
                    fontSize = 22.sp,
                    lineHeight = 26.sp,
                    fontWeight = FontWeight.Medium,
                    color = textColor
                )
            }

            if (favoriteList.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column(
                        modifier = Modifier.padding(top = 150.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        val emptyIcon =
                            if (isDarkTheme) R.drawable.noresultsdark
                            else R.drawable.noresultslight

                        Icon(
                            painter = painterResource(emptyIcon),
                            contentDescription = null,
                            modifier = Modifier.size(120.dp),
                            tint = Color.Unspecified
                        )

                        Text(
                            text = stringResource(R.string.emptyFavorites),
                            fontFamily = YS,
                            fontSize = 19.sp,
                            lineHeight = 22.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center,
                            color = textColor,
                            modifier = Modifier
                                .padding(horizontal = 24.dp)
                                .fillMaxWidth()
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    itemsIndexed(favoriteList) { _, track ->

                        TrackRow(
                            track = track.toAppTrack(),
                            isDarkTheme = isDarkTheme,
                            onClick = { onTrackClick(track.toAppTrack()) },
                            onLongClick = {
                                viewModel.toggleFavorite(track, false)
                            }
                        )
                    }
                }
            }
        }
    }
}
