package me.skrilltrax.bluetoothautoplay.services

import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothHeadset
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.util.Log
import me.skrilltrax.bluetoothautoplay.Constants
import me.skrilltrax.bluetoothautoplay.Utils
import me.skrilltrax.bluetoothautoplay.broadcastreceivers.BReceiver

class AutoPlayService : Service() {

    private lateinit var broadcastReceiver: BroadcastReceiver

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        startForeground(Constants.ID, Utils.createNotification(applicationContext))
        broadcastReceiver = BReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED)
        registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }

    override fun stopService(name: Intent): Boolean {
        unregisterReceiver(broadcastReceiver)
        return super.stopService(name)
    }
}