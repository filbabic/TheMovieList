package com.example.filip.movielist.ui.database.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.filip.movielist.R

/**
 * Created by Filip Babic @cobe
 */
class CachedMovieHolder constructor(holderView: View) : RecyclerView.ViewHolder(holderView) {

    private lateinit var movieTitle: TextView

    init {
        movieTitle = holderView.findViewById(R.id.cached_movie_title) as TextView
    }


    fun setTitle(title: String?) {
        movieTitle.text = title ?: ""
    }
}