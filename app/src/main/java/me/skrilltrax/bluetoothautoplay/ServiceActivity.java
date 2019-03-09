package me.skrilltrax.bluetoothautoplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import me.skrilltrax.bluetoothautoplay.services.AutoPlayService;

public class ServiceActivity extends Activity {

    public static final String TAG = "ServiceActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stopService(new Intent(getApplicationContext(), AutoPlayService.class));
        Log.d(TAG,"In OnCreate");
        finish();
    }
}
