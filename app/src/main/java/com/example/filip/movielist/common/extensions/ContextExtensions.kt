package com.example.filip.movielist.common.extensions

import android.content.Context
import android.widget.Toast

/**
 * Created by Filip Babic @cobe
 */

fun Context?.toast(message: String) {
    this?.let { Toast.makeText(this, message, Toast.LENGTH_SHORT).show() }
}