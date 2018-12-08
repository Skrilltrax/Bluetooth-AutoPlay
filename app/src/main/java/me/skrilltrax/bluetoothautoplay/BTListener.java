package me.skrilltrax.bluetoothautoplay;

import android.bluetooth.BluetoothProfile;
import android.content.Context;

public class BTListener implements BluetoothProfile.ServiceListener {

    Context context;

    BTListener(Context context) {
        this.context = context;
    }

    @Override
    public void onServiceConnected(int profile, BluetoothProfile proxy) {
        PlayMedia playMedia = new PlayMedia(context);
        playMedia.start();
    }

    @Override
    public void onServiceDisconnected(int profile) {
    }
}
