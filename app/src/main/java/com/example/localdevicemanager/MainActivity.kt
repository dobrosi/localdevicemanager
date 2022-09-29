package com.example.localdevicemanager

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.localdevicemanager.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.net.InetAddress
import javax.jmdns.JmDNS
import javax.jmdns.ServiceEvent
import javax.jmdns.ServiceListener


class MainActivity : AppCompatActivity() {
    companion object {
        private var ctx: Context? = null

        open fun toast(text: String) {
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(ctx, text, duration)
            toast.show()
        }
    }


    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ctx = applicationContext

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        toast("started")

        val jmdns: JmDNS = JmDNS.create(InetAddress.getLocalHost())
        jmdns.addServiceListener("_http._tcp.local.", SampleListener())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private class SampleListener : ServiceListener {
        override fun serviceAdded(event: ServiceEvent?) {
            toast("serviceAdded" + event!!.dns.inetAddress)
        }

        override fun serviceRemoved(event: ServiceEvent?) {
            toast("serviceRemoved" + event!!.dns.inetAddress)
        }

        override fun serviceResolved(event: ServiceEvent?) {
            toast("serviceResolved " + event!!.dns.inetAddress)
        }
    }
}