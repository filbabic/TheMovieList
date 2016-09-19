package com.example.filip.movielist.view

import com.example.filip.movielist.model.MovieListModel

/**
 * Created by Filip Babic @cobe
 */
interface FavoriteMoviesView : BaseView {

    fun setAdapterItems(items: MutableList<MovieListModel>?)

    fun showNoMoviesError()
}