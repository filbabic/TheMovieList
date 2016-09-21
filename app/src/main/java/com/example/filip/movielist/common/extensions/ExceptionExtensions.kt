package com.example.filip.movielist.common.extensions

import com.example.filip.movielist.BuildConfig

/**
 * Created by Filip Babic @cobe
 */


fun Throwable?.logSelf() {
    if (BuildConfig.DEBUG) {
        this?.printStackTrace()
    }
}