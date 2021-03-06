package me.skrilltrax.bluetoothautoplay.services;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothHeadset;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import me.skrilltrax.bluetoothautoplay.Utils;
import me.skrilltrax.bluetoothautoplay.broadcastreceivers.BReceiver;

public class AutoPlayService extends Service {

    public static final String TAG = "AutoPlayService";
    public static int ID = 123;

    BroadcastReceiver broadcastReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "In onCreate");
        startForeground(ID,Utils.createNotification(getApplicationContext()));
        broadcastReceiver = new BReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"In onDestroy");
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public boolean stopService(Intent name) {
        unregisterReceiver(broadcastReceiver);
        return super.stopService(name);
    }
}

