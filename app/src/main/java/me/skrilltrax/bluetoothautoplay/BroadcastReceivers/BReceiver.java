package me.skrilltrax.bluetoothautoplay.BroadcastReceivers;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import me.skrilltrax.bluetoothautoplay.Services.PlayMedia;

public class BReceiver extends BroadcastReceiver {

    public static final String TAG = "BReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e(TAG, "In BReceiver");
        int state;
        Bundle extras = intent.getExtras();
        if (extras != null) {
            state = extras.getInt(BluetoothAdapter.EXTRA_CONNECTION_STATE);
            Log.e(TAG, String.valueOf(state));
            if (intent.getAction().equals(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED)) {
                if (state == BluetoothAdapter.STATE_CONNECTED) {
                    try {
                        Thread.sleep(3000);
                        PlayMedia playMedia = new PlayMedia(context);
                        playMedia.start();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}