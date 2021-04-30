package com.example.androidalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * Escuta eventos para parar
 */
class StopAlarmBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        AlarmPlayer.instance.stop()
        NotificationHelper().dismissNotification(context!!)
    }
}