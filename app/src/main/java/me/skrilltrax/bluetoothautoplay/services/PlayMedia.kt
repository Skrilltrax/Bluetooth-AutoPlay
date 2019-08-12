package me.skrilltrax.bluetoothautoplay.services

import android.content.ComponentName
import android.content.Context
import android.media.session.MediaController
import android.media.session.MediaSessionManager
import android.util.Log
import android.view.KeyEvent

class PlayMedia(context: Context) {

    private var mediaControllers: List<MediaController>

    init {
        val mediaSessionManager = context.getSystemService(Context.MEDIA_SESSION_SERVICE) as MediaSessionManager
        mediaControllers = mediaSessionManager.getActiveSessions(ComponentName(
                        "me.skrilltrax.bluetoothautoplay",
                        "me.skrilltrax.bluetoothautoplay.services.NLService"))
    }

    fun start() {
        if (mediaControllers.isNotEmpty()) {

            val mediaController = mediaControllers[0]
            mediaController.dispatchMediaButtonEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY))
            mediaController.dispatchMediaButtonEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PLAY))
            Log.e("BTConnectionReceiver", "Key Event Dispatched")
        }
    }
}
