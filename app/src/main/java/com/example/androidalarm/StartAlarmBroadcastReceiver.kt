package com.example.androidalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * Escuta eventos para iniciar um alarme
 */
class StartAlarmBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        AlarmPlayer.instance.playAlarm(context!!)
        NotificationHelper().displayNotification(context)
    }
}