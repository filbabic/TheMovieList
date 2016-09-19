package com.example.filip.movielist.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.filip.movielist.App

/**
 * Created by Filip Babic @cobe
 */
class NetworkUtils {

    companion object {

        fun isInternetAvailable(): Boolean {
            val manager = App.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo: NetworkInfo = manager.activeNetworkInfo

            return networkInfo.isAvailable && networkInfo.isConnectedOrConnecting
        }
    }
}