package me.skrilltrax.bluetoothautoplay

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.skrilltrax.bluetoothautoplay.services.AutoPlayService
import me.skrilltrax.bluetoothautoplay.services.NLService

class MainActivity : AppCompatActivity() {

    private var notificationEnabled: Boolean = false
    private var serviceRunning: Boolean = false
    private lateinit var autoplayStatus: TextView
    private lateinit var button: Button
    private lateinit var serviceStatus: TextView
    private lateinit var serviceButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationEnabled = NLService.isEnabled(this@MainActivity)
        serviceRunning = Utils.isServiceRunning(AutoPlayService::class.java, this)

        findViews()
        createUI()
        setupListeners()
    }

    private fun setupListeners() {
        serviceButton.setOnClickListener { v ->
            val context = v.context
            val i = Intent(v.context, AutoPlayService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(i)
            } else {
                startService(i)
            }
            Toast.makeText(context, "Service Started", Toast.LENGTH_SHORT).show()
            createUI()
        }
    }

    private fun findViews() {
        autoplayStatus = findViewById(R.id.notification_status)
        button = findViewById(R.id.button)
        serviceStatus = findViewById(R.id.autoplay_status)
        serviceButton = findViewById(R.id.service_button)
    }

    override fun onResume() {
        super.onResume()
        notificationEnabled = NLService.isEnabled(this@MainActivity)
        serviceRunning = Utils.isServiceRunning(AutoPlayService::class.java, this)
        createUI()
    }

    fun onClick() {

        notificationEnabled = NLService.isEnabled(this@MainActivity)
        if (!notificationEnabled) {
            val alertDialog = Utils.createAlertDialog(this)
            alertDialog.show()
        } else {
            startActivity(Intent(Constants.ACTION_NOTIFICATION_LISTENER))
        }
    }

    private fun createUI() {
        notificationEnabled = NLService.isEnabled(this@MainActivity)
        serviceRunning = Utils.isServiceRunning(AutoPlayService::class.java, applicationContext)
        if (!notificationEnabled) {
            autoplayStatus.text = getString(R.string.disabled)
            button.text = getString(R.string.enable)
        } else {
            autoplayStatus.text = getString(R.string.enabled)
            button.text = getString(R.string.disable)
        }
        if (!serviceRunning) {
            serviceStatus.text = getString(R.string.disabled)
        } else {
            serviceStatus.text = getString(R.string.enabled)
        }
    }
}