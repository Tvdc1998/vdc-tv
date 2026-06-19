package com.vdc.tv.core.presentation.dummy

import com.vdc.tv.models.AudioChannel
import com.vdc.tv.models.AudioCodec
import com.vdc.tv.models.DisplayProfile
import com.vdc.tv.models.Resolution
import com.vdc.tv.models.VideoCodec
import com.vdc.tv.models.VideoMetadata

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
