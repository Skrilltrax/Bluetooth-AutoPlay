package me.skrilltrax.bluetoothautoplay;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class BTConnectionReceiver extends BroadcastReceiver {

    public static final String TAG = "BTConnectionReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e(TAG, "in onReceive");
        if (intent.getAction().equals(BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED)) {
            Log.e(TAG,"STATE_CHANGED");
        }
        if (intent.getAction().equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {
            Log.e(TAG,"BOND_STATE_CHANGED");
        }
        if (intent.getAction().equals(BluetoothDevice.ACTION_ACL_CONNECTED)) {
            Log.e(TAG,"ACL_CONNECTED");
//            PlayMedia playMedia = new PlayMedia(context);
//            playMedia.start();
        }
        if (intent.getAction().equals(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED)) {
            Log.e(TAG,"CONNECTION_STATE_CHANGED");
        }
    }
}
