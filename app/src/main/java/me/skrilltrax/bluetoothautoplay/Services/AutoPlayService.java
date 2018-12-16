package me.skrilltrax.bluetoothautoplay.Services;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import me.skrilltrax.bluetoothautoplay.BroadcastReceivers.BReceiver;

public class AutoPlayService extends Service {

    public static final String TAG = "AutoPlayService";
    public static boolean isRunning  = false;

    BroadcastReceiver broadcastReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "In onCreate");

        broadcastReceiver = new BReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED);
        registerReceiver(broadcastReceiver, intentFilter);
        isRunning = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"In onDestroy");
        unregisterReceiver(broadcastReceiver);
        isRunning = false;
    }
}

