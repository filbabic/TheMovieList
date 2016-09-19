package com.example.filip.movielist.presentation

import com.example.filip.movielist.common.extensions.isValid
import com.example.filip.movielist.common.extensions.transformToMovieListModels
import com.example.filip.movielist.data.database.DatabaseManager
import com.example.filip.movielist.model.MovieDetailsResponse
import com.example.filip.movielist.view.FavoriteMoviesView

/**
 * Created by Filip Babic @cobe
 */
class FavoriteMoviesPresenterImpl(private val databaseManager: DatabaseManager) : FavoriteMoviesPresenter {

    private lateinit var favoriteMoviesView: FavoriteMoviesView

    override fun getFavoriteMovies() {
        val movies: List<MovieDetailsResponse> = databaseManager.getFavoriteMovies()

        if (movies.isValid()) {
            favoriteMoviesView.setAdapterItems(movies.transformToMovieListModels())
        } else {
            favoriteMoviesView.showNoMoviesError()
        }
    }

    override fun cancelSubscriptions() {
    }

    override fun onRequestsCancelled() {
    }

    override fun setView(view: FavoriteMoviesView) {
        favoriteMoviesView = view
    }
}