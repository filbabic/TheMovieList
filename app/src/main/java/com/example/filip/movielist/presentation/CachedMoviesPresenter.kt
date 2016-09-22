package com.example.filip.movielist.presentation

import com.example.filip.movielist.view.CachedMoviesView

/**
 * Created by Filip Babic @cobe
 */
interface CachedMoviesPresenter : Presenter<CachedMoviesView> {

    fun getCachedMovies()

    fun deleteMovies()

    fun handleUserClickedClearItems()

    fun handleUserMenuClick(itemId: Int)
}