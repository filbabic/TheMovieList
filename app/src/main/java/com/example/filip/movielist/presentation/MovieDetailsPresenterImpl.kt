package com.example.filip.movielist.presentation

import com.example.filip.movielist.common.extensions.logSelf
import com.example.filip.movielist.data.database.DatabaseManager
import com.example.filip.movielist.data.handler.MovieDetailsHandler
import com.example.filip.movielist.interaction.MovieDetailsInteractor
import com.example.filip.movielist.model.MovieDetailsResponse
import com.example.filip.movielist.view.MovieDetailsView
import rx.SingleSubscriber

/**
 * Created by Filip Babic @cobe
 */
class MovieDetailsPresenterImpl constructor(val movieDetailsInteractor: MovieDetailsInteractor, val databaseManager: DatabaseManager, val movieDetailsHandler: MovieDetailsHandler) : MovieDetailsPresenter {

    private var subscriber: SingleSubscriber<MovieDetailsResponse>? = bindMoviesSubscriber()
    private lateinit var movieDetailsView: MovieDetailsView

    private var movieID: Int = 0

    override fun getMovie(hasInternet: Boolean) {
        if (hasInternet) {
            movieDetailsView.getMovieFromNetwork()
        } else {
            movieDetailsView.getMovieFromDatabase()
        }
    }

    override fun setMovieId(movieID: Int) {
        this.movieID = movieID
    }

    override fun handleMovieFavoriteClick() {
        movieDetailsHandler.changeFavoriteStatus()

        movieDetailsView.setFloatingButtonStatus(isFavorite = movieDetailsHandler.getFavoriteStatus())
    }

    override fun handleMovieStatus() {
        databaseManager.setMovieFavoriteStatus(movieDetailsResponse = movieDetailsHandler.getData())
    }

    override fun handleScrolling(scrollY: Int, isShown: Boolean) {
        if (scrollY > 0 && isShown) {
            movieDetailsView.hideFloatingButton()
        } else {
            movieDetailsView.showFloatingButton()
        }
    }

    override fun requestMovieFromDatabase() {
        movieDetailsHandler.setData(databaseManager.getMovieDetailsBy(id = movieID))

        handleMovieDisplay()
    }

    override fun requestMovieFromNetwork() {
        movieDetailsInteractor.getMovieBy(movieId = movieID, subscriber = bindMoviesSubscriber())
    }

    override fun cancelSubscriptions() {
        movieDetailsInteractor.cancelRequest(arrayOf(subscriber))
        movieDetailsView.onRequestsCancelled()
    }

    override fun setView(view: MovieDetailsView) {
        movieDetailsView = view
    }

    internal fun bindMoviesSubscriber(): SingleSubscriber<MovieDetailsResponse> {
        subscriber = subscriber ?: object : SingleSubscriber<MovieDetailsResponse>() {

            override fun onSuccess(value: MovieDetailsResponse?) {
                val movie = databaseManager.getMovieDetailsBy(id = movieID)
                value?.apply { isFavorite = movie.isFavorite }
                movieDetailsHandler.setData(value)
                handleMovieDisplay()
            }

            override fun onError(error: Throwable?) {
                error.logSelf()
                movieDetailsView.showMovieRequestError()
            }
        }

        return subscriber!!
    }

    private fun handleMovieDisplay() {
        movieDetailsView.setFloatingButtonStatus(isFavorite = movieDetailsHandler.getFavoriteStatus())
        movieDetailsView.setMovieDetails(details = movieDetailsHandler.getDescription())
        movieDetailsView.setMovieGenres(genres = movieDetailsHandler.getGenres())
        movieDetailsView.setMoviePoster(posterPath = movieDetailsHandler.getPosterPath())
        movieDetailsView.setMovieReleaseDate(releaseDate = movieDetailsHandler.getReleaseDate())
        movieDetailsView.setMovieVoteAverage(voteAverage = movieDetailsHandler.getVoteAverage())
        movieDetailsView.setMovieReleaseStatus(releaseStatus = movieDetailsHandler.getStatus())
        movieDetailsView.setMovieRevenue(revenue = movieDetailsHandler.getRevenue())
        movieDetailsView.setMovieTitle(title = movieDetailsHandler.getTitle())
        movieDetailsView.setMovieRuntime(runtime = movieDetailsHandler.getRuntime())
    }
}