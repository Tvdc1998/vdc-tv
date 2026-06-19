<<<<<<< HEAD
package com.vdc.tv.core.presentation.dummy

import com.vdc.tv.models.AudioChannel
import com.vdc.tv.models.AudioCodec
import com.vdc.tv.models.DisplayProfile
import com.vdc.tv.models.Resolution
import com.vdc.tv.models.VideoCodec
import com.vdc.tv.models.VideoMetadata
=======
package dev.jdtech.jellyfin.core.presentation.dummy

import dev.jdtech.jellyfin.models.AudioChannel
import dev.jdtech.jellyfin.models.AudioCodec
import dev.jdtech.jellyfin.models.DisplayProfile
import dev.jdtech.jellyfin.models.Resolution
import dev.jdtech.jellyfin.models.VideoCodec
import dev.jdtech.jellyfin.models.VideoMetadata
>>>>>>> b69d89e43a3035044e06a8a08f11960b3b6083e8

val dummyVideoMetadata =
    VideoMetadata(
        size = 1000000000,
        videoTracks = emptyList(),
        audioTracks = emptyList(),
        subtitleTracks = emptyList(),
        resolution = listOf(Resolution.HD),
        videoCodecs = listOf(VideoCodec.AV1),
        displayProfiles = listOf(DisplayProfile.HDR10),
        audioChannels = listOf(AudioChannel.CH_5_1),
        audioCodecs = listOf(AudioCodec.OPUS),
        isAtmos = listOf(false),
    )
