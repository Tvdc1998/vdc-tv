<<<<<<< HEAD
package com.vdc.tv.ui.components.player
=======
package dev.jdtech.jellyfin.ui.components.player
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

import androidx.annotation.IntRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.debounce

class VideoPlayerState
internal constructor(@param:IntRange(from = 0) private val hideSeconds: Int) {
    private var _controlsVisible by mutableStateOf(true)
    val controlsVisible
        get() = _controlsVisible

    fun showControls(seconds: Int = hideSeconds) {
        _controlsVisible = true
        channel.trySend(seconds)
    }

    private val channel = Channel<Int>(CONFLATED)

    @OptIn(FlowPreview::class)
    suspend fun observe() {
        channel.consumeAsFlow().debounce { it.toLong() * 1000 }.collect { _controlsVisible = false }
    }
}

@Composable
fun rememberVideoPlayerState(@IntRange(from = 0) hideSeconds: Int = 2) =
    remember { VideoPlayerState(hideSeconds = hideSeconds) }
        .also { LaunchedEffect(it) { it.observe() } }
