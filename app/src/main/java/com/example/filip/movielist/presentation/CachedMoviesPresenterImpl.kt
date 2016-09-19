package com.example.filip.movielist.presentation

import com.example.filip.movielist.common.extensions.getMoviesTitles
import com.example.filip.movielist.common.extensions.isValid
import com.example.filip.movielist.data.database.DatabaseManager
import com.example.filip.movielist.view.CachedMoviesView

/**
 * Created by Filip Babic @cobe
 */
class CachedMoviesPresenterImpl(private val databaseManager: DatabaseManager) : CachedMoviesPresenter {

    private lateinit var cachedMoviesView: CachedMoviesView

    override fun deleteMovies() {
        databaseManager.deleteAllMovies()

        cachedMoviesView.clearAdapter()
    }

    override fun getCachedMovies() {
        val movies = databaseManager.getAllMovies()

        if (movies.isValid()) {
            cachedMoviesView.setAdapterItems(items = movies.getMoviesTitles())
        } else {
            cachedMoviesView.showNoMoviesInDatabaseError()
        }

        cachedMoviesView.setNumberOfMovies(movies.size)
    }

    override fun handleUserClickedClearItems() {
        cachedMoviesView.showClearDatabaseDialog()
    }

    override fun cancelSubscriptions() {
    }

    override fun onRequestsCancelled() {
    }

    override fun setView(view: CachedMoviesView) {
        cachedMoviesView = view
    }
}