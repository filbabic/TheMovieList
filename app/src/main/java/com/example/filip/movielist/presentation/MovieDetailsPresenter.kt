package com.example.filip.movielist.presentation

import com.example.filip.movielist.view.MovieDetailsView

/**
 * Created by Filip Babic @cobe
 */
interface MovieDetailsPresenter : Presenter<MovieDetailsView> {

    fun setMovieId(movieID: Int)

    fun handleScrolling(scrollY: Int, isShown: Boolean)

    fun handleMovieStatus()

    fun handleMovieFavoriteClick()

    fun getMovie(hasInternet: Boolean)

    fun requestMovieFromNetwork()

    fun requestMovieFromDatabase()
}