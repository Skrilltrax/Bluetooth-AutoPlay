package me.skrilltrax.bluetoothautoplay

import android.app.Application
import android.os.Build

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Utils.createNotificationChannel(this)
        }
    }
}