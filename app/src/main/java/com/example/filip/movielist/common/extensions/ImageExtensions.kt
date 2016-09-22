package com.example.filip.movielist.common.extensions

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * Created by Filip Babic @cobe
 */

fun ImageView?.loadImage(from: Context, url: String) {
    this?.let { Glide.with(from).load(url).diskCacheStrategy(DiskCacheStrategy.RESULT).into(this) }
}