package com.example.androidalarm

import android.content.Context
import android.media.MediaPlayer
import android.media.RingtoneManager

/**
 * Responsável pelo efeito sonoro do alarme
 */

class AlarmPlayer private constructor() {
    private var player: MediaPlayer? = null
    fun playAlarm(context: Context) {
        stop()
        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        player = MediaPlayer.create(context, notification)
        player?.start()
    }

    fun stop() {
        try {
            if (player != null && player!!.isPlaying) {
                player!!.stop()
            }
        } catch (e: IllegalStateException) {
        } finally {
            if (player != null) {
                player!!.release()
            }
        }
    }

    companion object {
        val instance = AlarmPlayer()
    }
}