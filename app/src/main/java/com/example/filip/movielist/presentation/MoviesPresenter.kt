package com.example.filip.movielist.presentation

import com.example.filip.movielist.view.MoviesListView

/**
 * Created by Filip Babic @cobe
 */
interface MoviesPresenter : Presenter<MoviesListView> {

    fun getMovies()

    fun requestMoviesFromNetwork()

    fun setMovieTypeKey(key: String?)

    fun requestMoviesFromDatabase()
}