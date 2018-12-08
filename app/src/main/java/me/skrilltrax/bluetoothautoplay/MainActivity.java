package me.skrilltrax.bluetoothautoplay;

import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.session.MediaController;
import android.media.session.MediaSessionManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(this,me.skrilltrax.bluetoothautoplay.NLService.class);
        startService(i);
        BTListener btListener = new BTListener(this);

    }

    public void onClick(View view) {
        MediaSessionManager mediaSessionManager = (MediaSessionManager)
                this.getSystemService(Context.MEDIA_SESSION_SERVICE);

        List<MediaController> mediaControllers = mediaSessionManager
                .getActiveSessions(new ComponentName("me.skrilltrax.bluetoothautoplay", "me.skrilltrax.bluetoothautoplay.NLService"));

        Log.e("SIZE",String.valueOf(mediaControllers.size()));

        if (mediaControllers.size() > 0) {
            MediaController mediaController = mediaControllers.get(0);
            mediaController.dispatchMediaButtonEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY));
            mediaController.dispatchMediaButtonEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PLAY));

        }
    }
}
