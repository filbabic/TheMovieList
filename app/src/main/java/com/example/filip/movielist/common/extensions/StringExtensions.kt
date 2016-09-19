package com.example.filip.movielist.common.extensions

import com.example.filip.movielist.constants.Constants

/**
 * Created by Filip Babic @cobe
 */


fun String?.isValid(): Boolean {
    return this != null && !isEmpty() && !trim().isEmpty()
}

fun String.toKey(): String {
    when (this) {
        Constants.MOVIE_TYPE_TOP_RATED -> return Constants.MOVIE_TYPE_KEY_TOP_RATED

        Constants.MOVIE_TYPE_UPCOMING -> return toLowerCase()

        Constants.MOVIE_TYPE_POPULAR -> return toLowerCase()

        else -> return ""
    }
}