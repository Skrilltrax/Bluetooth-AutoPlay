package me.skrilltrax.bluetoothautoplay.broadcastreceivers

import android.bluetooth.BluetoothHeadset
import android.bluetooth.BluetoothProfile
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log

import me.skrilltrax.bluetoothautoplay.services.PlayMedia

class BReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val state: Int
        val extras = intent.extras
        if (extras != null) {
            state = extras.getInt(BluetoothProfile.EXTRA_STATE)
            if (intent.action == BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED) {
                if (state == BluetoothProfile.STATE_CONNECTED) {
                    try {
                        if (Build.MANUFACTURER.equals("OnePlus", true))
                            Thread.sleep(3000)
                        else
                            Thread.sleep(1200)
                        val playMedia = PlayMedia(context)
                        playMedia.start()
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                }
            }
        }
    }
}
