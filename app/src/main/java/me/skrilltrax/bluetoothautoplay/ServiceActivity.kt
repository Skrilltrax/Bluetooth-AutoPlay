package me.skrilltrax.bluetoothautoplay

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import me.skrilltrax.bluetoothautoplay.services.AutoPlayService

class ServiceActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stopService(Intent(applicationContext, AutoPlayService::class.java))
        finish()
    }

}
