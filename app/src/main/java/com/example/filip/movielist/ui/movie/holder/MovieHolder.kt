package com.example.filip.movielist.ui.movie.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.filip.movielist.R
import com.example.filip.movielist.common.extensions.isValid
import com.example.filip.movielist.common.extensions.loadImage
import com.example.filip.movielist.constants.Constants
import com.example.filip.movielist.listeners.ItemListener

/**
 * Created by Filip Babic @cobe
 */
class MovieHolder(holderView: View, private val itemListener: ItemListener?) : RecyclerView.ViewHolder(holderView) {

    internal val moviePoster: ImageView
    private val movieTitle: TextView
    private val movieDetails: TextView
    private val movieReleaseDate: TextView

    internal var movieId: Int = 0

    init {
        moviePoster = holderView.findViewById(R.id.movie_item_poster) as ImageView
        movieTitle = holderView.findViewById(R.id.movie_item_title) as TextView
        movieDetails = holderView.findViewById(R.id.movie_item_details) as TextView
        movieReleaseDate = holderView.findViewById(R.id.movie_item_release_date) as TextView

        moviePoster.setOnClickListener { startDetailsActivity(viewToAnimate = it) }
    }

    private fun startDetailsActivity(viewToAnimate: View) {
        itemListener?.onItemClick(movieId, viewToAnimate)
    }

    internal fun loadMovieImage(posterPath: String?) {
        if (posterPath.isValid()) {
            moviePoster.loadImage(itemView.context, String.format(Constants.MOVIE_POSTER_FORMAT, posterPath))
        }
    }

    internal fun setMovieTitle(title: String?) {
        if (title.isValid()) {
            movieTitle.text = title
        }
    }

    internal fun setMovieDetails(details: String?) {
        if (details.isValid()) {
            movieDetails.text = details
        }
    }

    internal fun setMovieReleaseDate(releaseDate: String?) {
        if (releaseDate.isValid()) {
            movieReleaseDate.text = releaseDate
        }
    }
}