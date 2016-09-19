package com.example.filip.movielist.presentation

import com.example.filip.movielist.view.FavoriteMoviesView


/**
 * Created by Filip Babic @cobe
 */
interface FavoriteMoviesPresenter : Presenter<FavoriteMoviesView> {

    fun getFavoriteMovies()
}