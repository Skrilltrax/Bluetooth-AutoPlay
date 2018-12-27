package me.skrilltrax.bluetoothautoplay;

import android.app.Service;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import me.skrilltrax.bluetoothautoplay.services.AutoPlayService;
import me.skrilltrax.bluetoothautoplay.services.NLService;

public class MainActivity extends AppCompatActivity {

    public static final String ACTION_NOTIFICATION_LISTENER = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";

    boolean notificationEnabled;
    boolean serviceRunning;
    private TextView autoplayStatus;
    private Button button;
    private TextView serviceStatus;

    public static final String TAG = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MAIN_ACTIVITY", "HERE");

        notificationEnabled = NLService.isEnabled(getApplicationContext());

        autoplayStatus = findViewById(R.id.notification_status);
        button = findViewById(R.id.button);
        serviceStatus = findViewById(R.id.autoplay_status);
        Button serviceButton = findViewById(R.id.service_button);

        createUI();

        serviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "In OnClickStartService");
                Context context = v.getContext();
                Intent i = new Intent(v.getContext(),AutoPlayService.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(i);
                } else {
                    startService(i);
                }
                Toast.makeText(context,"Service Started",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"RESUMED");
        notificationEnabled = NLService.isEnabled(getApplicationContext());
        createUI();
    }

    public void onClick(View view) {

        if(!notificationEnabled) {
            AlertDialog alertDialog = Utils.createAlertDialog(this);
            alertDialog.show();
        } else {
            startActivity(new Intent(ACTION_NOTIFICATION_LISTENER));
        }
    }

    private void createUI() {
        notificationEnabled = NLService.isEnabled(getApplicationContext());
        Log.e(TAG,String.valueOf(notificationEnabled));
        if(!notificationEnabled) {
            autoplayStatus.setText(getString(R.string.disabled));
            autoplayStatus.setTextColor(Color.RED);
            button.setText(getString(R.string.enable));
        } else {
            autoplayStatus.setText(getString(R.string.enabled));
            autoplayStatus.setTextColor(Color.GREEN);
            button.setText(getString(R.string.disable));
        }
    }
}