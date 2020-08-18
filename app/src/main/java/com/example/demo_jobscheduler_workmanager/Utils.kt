package com.example.demo_jobscheduler_workmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ClipDescription
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

/**
 * Method for sleeping for a fixed about of time to emulate slower work
 */
fun sleep() {
    try {
        Thread.sleep(DELAY_TIME_MILLIS, 0)
        Log.d(LOG, Thread.currentThread().name)
    } catch (e: InterruptedException) {
        Log.e(LOG, e.message + "")
    }
}

fun makeStatusNotification(
    notificationChannelId: String,
    notificationChannelName: String,
    channelDescription: String,
    notificationId: Int,
    notificationTitle: String,
    notificationMessage: String,
    context: Context
) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            notificationChannelId,
            notificationChannelName,
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.description = channelDescription

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        notificationManager?.createNotificationChannel(channel)
    }

    // Create the notification
    val builder = NotificationCompat.Builder(context, notificationChannelId)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle(notificationTitle)
        .setContentText(notificationMessage)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setVibrate(LongArray(0))

    // Show the notification
    NotificationManagerCompat.from(context).notify(notificationId, builder.build())
}