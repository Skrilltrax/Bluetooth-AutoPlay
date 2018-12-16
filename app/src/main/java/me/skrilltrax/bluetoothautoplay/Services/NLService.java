package me.skrilltrax.bluetoothautoplay.Services;

import android.content.Context;
import android.service.notification.NotificationListenerService;
import android.util.Log;

import androidx.core.app.NotificationManagerCompat;

public class NLService extends NotificationListenerService {

    public static final String TAG = "NLSERVICE";

    @Override
    public void onListenerConnected() {
        Log.e(TAG,"CONNECTED");
    }

    public static boolean isEnabled (Context context) {

        Log.e(TAG,String.valueOf(NotificationManagerCompat.getEnabledListenerPackages(context)));
        return NotificationManagerCompat
                .getEnabledListenerPackages(context)
                .contains(context.getPackageName());
    }
}