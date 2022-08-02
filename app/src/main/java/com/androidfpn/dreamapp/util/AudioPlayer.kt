package com.androidfpn.dreamapp.util

import android.net.Uri
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import java.lang.RuntimeException
import javax.inject.Inject

class AudioPlayer @Inject constructor() {

    @Inject
    lateinit var exoPlayer: ExoPlayer

    var audioFile: String = ""

    fun startPlayer() {
        val mediaItem: MediaItem = MediaItem.fromUri(Uri.parse(audioFile))
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
//        exoPlayer.play()
    }

    fun getExoPlayerInstance(): ExoPlayer {
        if (::exoPlayer.isInitialized) {
            return exoPlayer
        } else {
            throw RuntimeException()
        }
    }
}