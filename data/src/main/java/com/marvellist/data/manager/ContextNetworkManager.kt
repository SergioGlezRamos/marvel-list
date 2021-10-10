package com.marvellist.data.manager

import android.content.Context
import android.net.ConnectivityManager
import com.marvellist.domain.manager.NetworkManager

class ContextNetworkManager(private val context: Context) : NetworkManager {

    override fun isNetworkAvailable(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}