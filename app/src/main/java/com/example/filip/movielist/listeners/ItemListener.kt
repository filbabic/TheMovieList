package com.example.filip.movielist.listeners

import android.view.View

/**
 * Created by Filip Babic @cobe
 */
interface ItemListener {

    fun onItemClick(movieID: Int, viewToTransit: View)

    fun onLastItemReached()
}