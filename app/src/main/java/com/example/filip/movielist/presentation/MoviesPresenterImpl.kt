package com.example.filip.movielist.presentation

import com.example.filip.movielist.common.extensions.logSelf
import com.example.filip.movielist.data.database.DatabaseManager
import com.example.filip.movielist.data.handler.MoviesHandler
import com.example.filip.movielist.interaction.MoviesInteractor
import com.example.filip.movielist.model.MovieListModel
import com.example.filip.movielist.model.MovieListResponse
import com.example.filip.movielist.view.MoviesListView
import rx.SingleSubscriber
import java.util.*

/**
 * Created by Filip Babic @cobe
 */
class MoviesPresenterImpl(val moviesInteractor: MoviesInteractor, val databaseManager: DatabaseManager, val moviesHandler: MoviesHandler) : MoviesPresenter {

    private lateinit var movieView: MoviesListView

    private var moviesSubscriber: SingleSubscriber<MovieListResponse>? = bindMoviesSubscriber()

    override fun getMovies(isInternetAvailable: Boolean) {
        moviesHandler.setPage(1)

        if (isInternetAvailable) {
            movieView.requestMovieFromNetwork()
        } else {
            movieView.requestMovieFromDatabase()
        }
    }

    override fun requestMoviesFromDatabase() {
        val movies: MutableList<MovieListModel> = ArrayList()
        movies.addAll(databaseManager.getMoviesBy(key = moviesHandler.getMovieType()))

        handleMovies()
        requestMoviesFromNetwork()
    }

    override fun requestMoviesFromNetwork() {
        var pageIndex = moviesHandler.getPage()

        moviesInteractor.getMoviesBy(movieType = moviesHandler.getMovieType(), page = pageIndex, subscriber = bindMoviesSubscriber())
        moviesHandler.setPage(++pageIndex)
    }

    internal fun bindMoviesSubscriber(): SingleSubscriber<MovieListResponse> {
        moviesSubscriber = moviesSubscriber ?: object : SingleSubscriber<MovieListResponse>() {

            override fun onSuccess(value: MovieListResponse?) {
                moviesHandler.setData(value)
                databaseManager.saveMoviesToDatabase(moviesHandler.getData()?.results)
                handleMovies()
            }

            override fun onError(error: Throwable?) {
                error.logSelf()
            }
        }

        return moviesSubscriber!!
    }

    private fun handleMovies() {
        movieView.setAdapterItems(moviesHandler.getData()?.results, moviesHandler.getPage() == 1)
        movieView.setListIsRefreshing(isRefreshing = false)
    }

    override fun setMovieTypeKey(key: String?) {
        moviesHandler.setMovieType(key)
    }

    override fun cancelSubscriptions() {
        moviesInteractor.cancelRequest(arrayOf(moviesSubscriber))

        movieView.onRequestsCancelled()
    }

    override fun setView(view: MoviesListView) {
        this.movieView = view
    }
}