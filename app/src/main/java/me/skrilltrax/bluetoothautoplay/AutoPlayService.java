package me.skrilltrax.bluetoothautoplay;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class AutoPlayService extends Service {

    public static final String TAG = "AutoPlayService";

    BroadcastReceiver broadcastReceiver;

    public class BReceiver extends BroadcastReceiver {
        public static final String TAG = "BReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {

            Log.e(TAG, "In BReceiver");
            int state;
            Bundle extras = intent.getExtras();
            Log.e("AAA",extras.toString());
            if (extras != null) {
                Log.e(TAG, "Extras");
                state = extras.getInt(BluetoothAdapter.EXTRA_CONNECTION_STATE);

                Log.e(TAG, String.valueOf(state));
                if (intent.getAction().equals(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED)) {
                    Log.e(TAG, "1st equal");

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
        Log.e(TAG, "WORKING HERE2");
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"In onDestroy");
        unregisterReceiver(broadcastReceiver);
    }
}

