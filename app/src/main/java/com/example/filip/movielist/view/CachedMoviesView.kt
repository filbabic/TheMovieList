package com.example.filip.movielist.view

/**
 * Created by Filip Babic @cobe
 */
interface CachedMoviesView : BaseView {

    fun setAdapterItems(items: List<String>?)

    fun setNumberOfMovies(numberOfMovies: Int)

    fun showNoMoviesInDatabaseError()

    fun showClearDatabaseDialog()

    fun handleClearDatabaseClick()

    fun handleHomeButtonClick()

    fun clearAdapter()
}