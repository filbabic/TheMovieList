package com.example.filip.movielist.view

import com.example.filip.movielist.model.MovieListModel

/**
 * Created by Filip Babic @cobe
 */
interface MoviesListView : BaseView {

    fun setAdapterItems(newItems: MutableList<MovieListModel>?, shouldReset: Boolean)

    fun requestMovieFromNetwork()

    fun requestMovieFromDatabase()

    fun setListIsRefreshing(isRefreshing: Boolean)
}