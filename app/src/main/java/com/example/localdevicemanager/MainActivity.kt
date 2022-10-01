package com.example.localdevicemanager

import android.app.Activity
import android.content.Context
import android.net.nsd.NsdManager
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView

class MainActivity : Activity() {
    companion object {
        lateinit var webview: WebView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webview = findViewById(R.id.webview)
        val webSettings: WebSettings = webview.getSettings()
        webSettings.javaScriptEnabled = true
        mdns()
    }

    private fun mdns() {
        Thread {
            MdnsListener().start((applicationContext.getSystemService(Context.NSD_SERVICE) as NsdManager))
        }.start()
    }
}