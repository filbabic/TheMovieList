package com.example.filip.movielist.presentation

import com.example.filip.movielist.common.extensions.addKey
import com.example.filip.movielist.common.extensions.cancelRequests
import com.example.filip.movielist.common.extensions.logSelf
import com.example.filip.movielist.common.utils.NetworkUtils
import com.example.filip.movielist.data.database.DatabaseManager
import com.example.filip.movielist.interaction.MoviesInteractor
import com.example.filip.movielist.model.MovieListModel
import com.example.filip.movielist.model.MovieListResponse
import com.example.filip.movielist.view.MoviesListView
import rx.SingleSubscriber
import java.util.*

/**
 * Created by Filip Babic @cobe
 */
class MoviesPresenterImpl(private val moviesInteractor: MoviesInteractor, private val databaseManager: DatabaseManager) : MoviesPresenter {

    private lateinit var movieView: MoviesListView

    private var moviesSubscriber: SingleSubscriber<MovieListResponse>?

    internal var page: Int
    internal lateinit var movieTypeKey: String

    init {
        page = 1

        moviesSubscriber = bindMoviesSubscriber()
    }

    private fun bindMoviesSubscriber(): SingleSubscriber<MovieListResponse> {
        moviesSubscriber = moviesSubscriber ?: object : SingleSubscriber<MovieListResponse>() {

            override fun onSuccess(value: MovieListResponse?) {
                databaseManager.saveMoviesToDatabase(value?.results?.addKey(movieTypeKey))
                movieView.setAdapterItems(value?.results, page == 1)
                movieView.setListIsRefreshing(false)
            }

            override fun onError(error: Throwable?) {
                error.logSelf()
            }
        }

        return moviesSubscriber!!
    }

    override fun getMovies() {
        page = 1
        if (NetworkUtils.isInternetAvailable()) {
            movieView.requestMovieFromNetwork()
        } else {
            movieView.requestMovieFromDatabase()
        }
    }

    override fun requestMoviesFromDatabase() {
        val movies: MutableList<MovieListModel> = ArrayList()
        movies.addAll(databaseManager.getMoviesBy(key = movieTypeKey))

        movieView.setAdapterItems(newItems = movies, shouldReset = page == 1)
        movieView.setListIsRefreshing(false)
        requestMoviesFromNetwork()
    }

    override fun requestMoviesFromNetwork() {
        moviesInteractor.getMoviesBy(movieType = movieTypeKey, page = page, subscriber = bindMoviesSubscriber())
        page++
    }

    override fun setMovieTypeKey(key: String?) {
        movieTypeKey = key ?: ""
    }

    override fun cancelSubscriptions() {
        cancelRequests(moviesSubscriber)
    }

    override fun onRequestsCancelled() {
    }

    override fun setView(view: MoviesListView) {
        this.movieView = view
    }
}