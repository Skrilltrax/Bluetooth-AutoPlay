package me.skrilltrax.bluetoothautoplay;

import android.content.Context;
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
import me.skrilltrax.bluetoothautoplay.Services.AutoPlayService;
import me.skrilltrax.bluetoothautoplay.Services.NLService;

public class MainActivity extends AppCompatActivity {

    public static final String ACTION_NOTIFICATION_LISTENER = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";
    public boolean usesService = false;

    boolean serviceEnabled;
    private TextView autoplayStatus;
    private Button button;


    public static final String TAG = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("MAIN_ACTIVITY", "HERE");

        serviceEnabled = NLService.isEnabled(getApplicationContext());
        autoplayStatus = findViewById(R.id.autoplay_status);
        button = findViewById(R.id.button);
        Button serviceButton = findViewById(R.id.service_button);

        createBroadcastUI();

        serviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "In OnClickStartService");
                Context context = v.getContext();
                Intent i = new Intent(v.getContext(),AutoPlayService.class);
                context.startService(i);
                Toast.makeText(context,"Service Started",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"RESUMED");
        serviceEnabled = NLService.isEnabled(getApplicationContext());
        createBroadcastUI();
    }

    public void onClick(View view) {

        if(!serviceEnabled) {
            AlertDialog alertDialog = createAlertDialog();
            alertDialog.show();
        } else {
            startActivity(new Intent(ACTION_NOTIFICATION_LISTENER));
        }
    }

    private void createBroadcastUI() {
        serviceEnabled = NLService.isEnabled(getApplicationContext());
        Log.e(TAG,String.valueOf(serviceEnabled));
        if(!serviceEnabled) {
            autoplayStatus.setText(getString(R.string.disabled));
            autoplayStatus.setTextColor(Color.RED);
            button.setText(getString(R.string.enable));
        } else {
            autoplayStatus.setText(getString(R.string.enabled));
            autoplayStatus.setTextColor(Color.GREEN);
            button.setText(getString(R.string.disable));
        }
    }

    private void createServiceUI() {

    }

    private AlertDialog createAlertDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.notification_listener);
        alertDialogBuilder.setMessage(R.string.notification_listener_service_explanation);
        alertDialogBuilder.setPositiveButton(R.string.yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(ACTION_NOTIFICATION_LISTENER));
                    }
                });
        alertDialogBuilder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return alertDialogBuilder.create();
    }
}