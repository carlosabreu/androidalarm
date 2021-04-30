package com.example.androidalarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import java.util.*

/**
 * Responsável por agendar um alarme ou cancelá-lo
 */
class AlarmHelper {

    companion object {
        private const val PENDING_INTENT_IDENTIFIER = 567
    }

    fun startAlarmManager(context: Context, seconds: Long) {
        val alarmMgr = context.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
        val alarmIntent = createPendingIntent(context)
        val milliseconds: Long = convertInputTimerToMilliseconds(seconds)
        val alarmClockInfo = AlarmManager.AlarmClockInfo(milliseconds, alarmIntent)
        alarmMgr.setAlarmClock(alarmClockInfo, alarmIntent)
    }

    fun stopAlarmManager(context: Context) {
        val alarmMgr = context.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
        val alarmIntent = createPendingIntent(context)
        alarmMgr.cancel(alarmIntent)
    }

    private fun createPendingIntent(context: Context): PendingIntent {
        val intent = Intent(
            context,
            StartAlarmBroadcastReceiver::class.java
        )

        return PendingIntent.getBroadcast(
            context,
            PENDING_INTENT_IDENTIFIER, intent, 0
        )
    }

    private fun convertInputTimerToMilliseconds(seconds: Long): Long {
        val time = Calendar.getInstance()
        time.add(Calendar.SECOND, seconds.toInt())
        return time.timeInMillis
    }
}