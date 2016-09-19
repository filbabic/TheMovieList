package com.example.filip.movielist.common.extensions

import android.util.Log
import com.example.filip.movielist.BuildConfig

/**
 * Created by Filip Babic @cobe
 */


private val TAG = "Log: "

fun Throwable?.logSelf() {
    if (BuildConfig.DEBUG) {
        Log.e(TAG, this?.message, this)
    }
}