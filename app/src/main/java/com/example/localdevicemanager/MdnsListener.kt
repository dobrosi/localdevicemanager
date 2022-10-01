package com.example.localdevicemanager

import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo

class MdnsListener {
    companion object {
        private const val SERVICE_TYPE = "_http._tcp."
    }

    fun start(nsdManager: NsdManager) {

        val discoveryListener = object : NsdManager.DiscoveryListener {
            override fun onDiscoveryStarted(regType: String) {
            }

            override fun onServiceFound(service: NsdServiceInfo) {
              nsdManager.resolveService(service, LocalResolveListener())
            }

            override fun onServiceLost(service: NsdServiceInfo) {
            }

            override fun onDiscoveryStopped(serviceType: String) {}

            override fun onStartDiscoveryFailed(serviceType: String, errorCode: Int) {
                nsdManager.stopServiceDiscovery(this)
            }

            override fun onStopDiscoveryFailed(serviceType: String, errorCode: Int) {
                nsdManager.stopServiceDiscovery(this)
            }
        }
        nsdManager.discoverServices(SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, discoveryListener)
    }
}