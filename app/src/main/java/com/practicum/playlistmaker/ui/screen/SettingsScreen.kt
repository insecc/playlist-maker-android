package com.practicum.playlistmaker.ui.screen

import android.content.Intent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.practicum.playlistmaker.R
import com.practicum.playlistmaker.ui.theme.YS

private val White = Color.White
private val Black = Color.Black
private val ScreenBackgroundLight = White
private val ListBackgroundLight = White
private val ListTextLight = Black
private val IconTintLight = Color(0xFFAEAFB4)
private val LightTrack = Color(0xFFE6E8EB)
private val LightKnob = Color(0xFFAEAFB4)
private val ScreenBackgroundDark = Color(0xFF1A1B22)
private val ListBackgroundDark = ScreenBackgroundDark
private val ListTextDark = White
private val IconTintDark = White
private val ActiveBlue = Color(0xFF3772E7)
private val DarkTrackFallback = Color(0xFF2C3E66)
private val DarkKnobFallback = Color(0xFFAEAFB4)

@Composable
fun CustomSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    isDarkTheme: Boolean,
    modifier: Modifier = Modifier
) {
    val knobOffset by animateDpAsState(if (checked) 17.dp else 0.dp)
    val trackColor by animateColorAsState(
        targetValue = when {
            checked -> ActiveBlue.copy(alpha = 0.48f)
            isDarkTheme -> DarkTrackFallback
            else -> LightTrack
        }
    )
    val knobColor by animateColorAsState(
        targetValue = when {
            checked -> ActiveBlue
            isDarkTheme -> DarkKnobFallback.copy(alpha = 0.6f)
            else -> LightKnob
        }
    )
    Box(
        modifier = modifier
            .width(35.dp)
            .height(18.dp)
            .clickable { onCheckedChange(!checked) }
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .width(35.dp)
                .height(12.dp)
                .clip(MaterialTheme.shapes.small)
                .background(trackColor)
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(18.dp)
                .offset(x = knobOffset)
                .background(knobColor, shape = CircleShape)
        )
    }
}

@Composable
fun SettingsScreen(
    isDarkTheme: Boolean,
    onToggleTheme: (Boolean) -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val titleColor = if (!isDarkTheme) ListTextLight else ListTextDark
    val screenBg = if (!isDarkTheme) ScreenBackgroundLight else ScreenBackgroundDark
    val shareMessage = stringResource(R.string.shareMessageText)
    val supportEmail = stringResource(R.string.supportMail)
    val supportSubject = stringResource(R.string.supportSubjectText)
    val supportBody = stringResource(R.string.supportMessageText)
    val agreementUrl = stringResource(R.string.YandexURL)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(screenBg)
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
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier.size(24.dp),
                    tint = titleColor
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = stringResource(R.string.settingsT),
                fontFamily = YS,
                fontSize = 22.sp,
                lineHeight = 26.sp,
                fontWeight = FontWeight.Medium,
                color = titleColor
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                SettingsMenuItem(
                    title = stringResource(R.string.settingsToggle),
                    isSwitch = true,
                    switchChecked = isDarkTheme,
                    onSwitchChange = onToggleTheme,
                    isDarkTheme = isDarkTheme
                )
            }
            item {
                SettingsMenuItem(
                    title = stringResource(R.string.settingsShareString),
                    iconResId = R.drawable.share,
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, shareMessage)
                        }
                        context.startActivity(Intent.createChooser(shareIntent, null))
                    },
                    isDarkTheme = isDarkTheme
                )
            }
            item {
                SettingsMenuItem(
                    title = stringResource(R.string.settingsSupportString),
                    iconResId = R.drawable.support,
                    onClick = {
                        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                            data = "mailto:".toUri()
                            putExtra(Intent.EXTRA_EMAIL, arrayOf(supportEmail))
                            putExtra(Intent.EXTRA_SUBJECT, supportSubject)
                            putExtra(Intent.EXTRA_TEXT, supportBody)
                        }
                        context.startActivity(emailIntent)
                    },
                    isDarkTheme = isDarkTheme
                )
            }
            item {
                SettingsMenuItem(
                    title = stringResource(R.string.settingsAgreement),
                    iconResId = R.drawable.chevronright,
                    onClick = {
                        val agreementIntent = Intent(Intent.ACTION_VIEW, agreementUrl.toUri())
                        context.startActivity(agreementIntent)
                    },
                    isDarkTheme = isDarkTheme
                )
            }
        }
    }
}

@Composable
fun SettingsMenuItem(
    title: String,
    isSwitch: Boolean = false,
    switchChecked: Boolean = false,
    onSwitchChange: (Boolean) -> Unit = {},
    onClick: () -> Unit = {},
    iconResId: Int? = null,
    isDarkTheme: Boolean = false
) {
    val textColor = if (!isDarkTheme) ListTextLight else ListTextDark
    val iconColor = if (!isDarkTheme) IconTintLight else IconTintDark
    val itemBackground = if (!isDarkTheme) ListBackgroundLight else ListBackgroundDark
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(61.dp)
            .background(itemBackground)
            .clickable(enabled = !isSwitch) { onClick() }
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontFamily = YS,
            fontSize = 16.sp,
            lineHeight = 19.sp,
            fontWeight = FontWeight.Normal,
            color = textColor,
            modifier = Modifier.weight(1f)
        )
        if (isSwitch) {
            CustomSwitch(
                checked = switchChecked,
                onCheckedChange = onSwitchChange,
                isDarkTheme = isDarkTheme
            )
        } else {
            iconResId?.let {
                Icon(
                    painter = painterResource(it),
                    contentDescription = title,
                    modifier = Modifier.size(24.dp),
                    tint = iconColor
                )
            }
        }
    }
}