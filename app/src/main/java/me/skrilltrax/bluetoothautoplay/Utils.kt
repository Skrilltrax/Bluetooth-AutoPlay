package me.skrilltrax.bluetoothautoplay

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat

object Utils {
    private const val CHANNEL_ID = "Main_Notification"
    private const val ACTION_STOP_SERVICE = "me.skrilltrax.BluetoothAuoPlay.STOP_SERVICE"

    internal fun createAlertDialog(context: Context): AlertDialog {
        return AlertDialog.Builder(context)
                .setTitle(R.string.notification_listener)
                .setMessage(R.string.notification_listener_service_explanation)
                .setPositiveButton(R.string.yes) { _, _ -> context.startActivity(Intent(Constants.ACTION_NOTIFICATION_LISTENER)) }
                .setNegativeButton(R.string.no) { _, _ -> }
                .create()
    }

    fun createNotification(context: Context): Notification {
        val intent = Intent(context, ServiceActivity::class.java)
        intent.action = ACTION_STOP_SERVICE
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        return NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setContentTitle("Service Running")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build()
    }

    @SuppressLint("InlinedApi")
    internal fun createNotificationChannel(context: Context) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val channel = NotificationChannel(
                CHANNEL_ID,
                "Bluetooth AutoPlay",
                NotificationManager.IMPORTANCE_LOW).apply {
        }
        channel.description = "Notification to enable foreground service"
        notificationManager.createNotificationChannel(channel)
    }

    internal fun isServiceRunning(serviceClass: Class<*>,
                                  context: Context): Boolean {
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }
}
