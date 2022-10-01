package com.example.localdevicemanager

import android.net.nsd.NsdManager.ResolveListener
import android.net.nsd.NsdServiceInfo
import com.example.localdevicemanager.MainActivity.Companion.webview

class LocalResolveListener: ResolveListener {
    override fun onResolveFailed(serviceInfo: NsdServiceInfo?, errorCode: Int) {
    }

    override fun onServiceResolved(serviceInfo: NsdServiceInfo) {
        val host = serviceInfo.getHost()
        if (serviceInfo.serviceName.contains("kaputelefon")) {
            webview.post { webview.loadUrl("http://${host}") }
        }
    }
}