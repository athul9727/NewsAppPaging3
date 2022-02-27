package com.example.newsapp_paging3.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.LiveData

class ConnectivityLiveData(private val connectivityManager: ConnectivityManager)
    : LiveData<Boolean>() {


    constructor(application: Application) :
            this(
        application.getSystemService(Context.CONNECTIVITY_SERVICE
                ) as ConnectivityManager)

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }
    }

    override fun onActive() {
        super.onActive()
        //when livedata has atleast one observers
        val builder = NetworkRequest.Builder()
        connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        //when livedata has no observers
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}