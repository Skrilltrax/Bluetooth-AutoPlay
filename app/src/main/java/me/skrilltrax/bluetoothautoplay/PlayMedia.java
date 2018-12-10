package me.skrilltrax.bluetoothautoplay;

import android.content.ComponentName;
import android.content.Context;
import android.media.session.MediaController;
import android.media.session.MediaSessionManager;
import android.util.Log;
import android.view.KeyEvent;

import java.util.List;

public class PlayMedia {

    private List<MediaController> mediaControllers;

    PlayMedia(Context context) {

        MediaSessionManager mediaSessionManager = (MediaSessionManager)
                context.getSystemService(Context.MEDIA_SESSION_SERVICE);
        mediaControllers = mediaSessionManager
                .getActiveSessions(new ComponentName("me.skrilltrax.bluetoothautoplay"
                                                    ,"me.skrilltrax.bluetoothautoplay.NLService"));
    }

    public void start() {
        Log.e("PlayMedia",String.valueOf(mediaControllers.size()));

        if(mediaControllers.size()>0) {

            MediaController mediaController = mediaControllers.get(0);
            mediaController.dispatchMediaButtonEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY));
            mediaController.dispatchMediaButtonEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PLAY));

        }
    }
}
