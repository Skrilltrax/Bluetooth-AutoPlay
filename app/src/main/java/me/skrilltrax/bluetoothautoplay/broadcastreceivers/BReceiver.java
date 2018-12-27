package me.skrilltrax.bluetoothautoplay.broadcastreceivers;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.AudioPlaybackConfiguration;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import me.skrilltrax.bluetoothautoplay.services.PlayMedia;

public class BReceiver extends BroadcastReceiver {

    public static final String TAG = "BReceiver";

    @Override
    public void onReceive(final Context context, Intent intent) {

        Log.e(TAG, "In BReceiver");
        int state;
        Bundle extras = intent.getExtras();
        if (extras != null) {
            state = extras.getInt(BluetoothProfile.EXTRA_STATE);
            Log.e(TAG, String.valueOf(state));
            if (intent.getAction().equals(BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED)) {
                if (state == BluetoothProfile.STATE_CONNECTED) {
                    try {
                        Thread.sleep(2000);
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