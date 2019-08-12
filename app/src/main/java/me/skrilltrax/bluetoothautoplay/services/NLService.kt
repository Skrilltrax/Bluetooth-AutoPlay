package me.skrilltrax.bluetoothautoplay.services

import android.content.Context
import android.service.notification.NotificationListenerService
import android.util.Log

import androidx.core.app.NotificationManagerCompat

class NLService : NotificationListenerService() {

    companion object {
        fun isEnabled(context: Context): Boolean {
            return NotificationManagerCompat
                    .getEnabledListenerPackages(context)
                    .contains(context.packageName)
        }
    }
}
