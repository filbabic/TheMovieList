package com.example.filip.movielist.ui.movie.adapter

import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.filip.movielist.R
import com.example.filip.movielist.common.extensions.fill
import com.example.filip.movielist.constants.Constants
import com.example.filip.movielist.listeners.ItemListener
import com.example.filip.movielist.model.MovieListModel
import com.example.filip.movielist.ui.movie.holder.MovieHolder
import java.util.*

/**
 * Created by Filip Babic @cobe
 */
class MoviesAdapter(private val itemListener: ItemListener?) : RecyclerView.Adapter<MovieHolder>() {

    private val movies: MutableList<MovieListModel> = ArrayList()

    fun setItems(items: List<MovieListModel>?, reset: Boolean) {
        movies.fill(with = items, reset = reset)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieHolder?, position: Int) {
        val movie = movies.getOrNull(position)

        holder?.movieId = movie?.movieID ?: -1

        holder?.loadMovieImage(movie?.moviePosterPath ?: "")
        holder?.setMovieDetails(movie?.movieDetails ?: "")
        holder?.setMovieTitle(movie?.movieTitle ?: "")
        holder?.setMovieReleaseDate(movie?.movieReleaseDate ?: "")

        ViewCompat.setTransitionName(holder?.moviePoster, Constants.TRANSITION_NAME)

        if (isLastItemReached(position)) {
            itemListener?.onLastItemReached()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieHolder {
        val holderView = LayoutInflater.from(parent!!.context).inflate(R.layout.movie_item_layout, parent, false)

        return MovieHolder(holderView = holderView, itemListener = itemListener)
    }

    private fun isLastItemReached(position: Int): Boolean {
        return position != 0 && position == itemCount - 1
    }
}