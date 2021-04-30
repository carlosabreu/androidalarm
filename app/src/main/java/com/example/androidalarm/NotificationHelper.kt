package com.example.androidalarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class NotificationHelper {

    companion object {
        const val NOTIFICATION_CHANNEL = "alarm manager notification channel"
        const val NOTIFICATION_TITLE = "Hora de acordar."
        const val NOTIFICATION_CANCEL_BUTTON = "Ok"
        const val NOTIFICATION_CODE = 45678
    }

    fun displayNotification(context: Context) {
        var notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager

        createNotificationChannel(notificationManager)

        val notification =
            NotificationCompat.Builder(context, NOTIFICATION_CHANNEL)
                .setContentTitle(NOTIFICATION_TITLE)
                .setTicker(NOTIFICATION_TITLE)
                .setSmallIcon(R.drawable.ic_schedule_black_24dp)
                .setOngoing(true)
                .setContentIntent(createActivityPendingIntent(context, MainActivity::class.java))
                .addAction(
                    android.R.drawable.ic_delete,
                    NOTIFICATION_CANCEL_BUTTON,
                    createBroadcastPendingIntent(context, StopAlarmBroadcastReceiver::class.java)
                )
                .setNotificationSilent()
                .build()

        notificationManager.notify(NOTIFICATION_CODE, notification);
    }

    fun dismissNotification(context: Context) {
        (context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager).cancel(
            NOTIFICATION_CODE
        )
    }

    private fun createBroadcastPendingIntent(
        context: Context,
        parameterClass: Class<*>
    ): PendingIntent {
        return PendingIntent.getBroadcast(context, 0, Intent(context, parameterClass), 0)
    }

    private fun createActivityPendingIntent(
        context: Context,
        parameterClass: Class<*>
    ): PendingIntent {
        return PendingIntent.getActivity(context, 0, Intent(context, parameterClass), 0)
    }

    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel =
            NotificationChannel(
                NOTIFICATION_CHANNEL,
                NOTIFICATION_CHANNEL,
                importance
            ).apply {
                description = NOTIFICATION_CHANNEL
            }
        notificationManager.createNotificationChannel(channel)
    }
}