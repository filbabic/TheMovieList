package com.example.filip.movielist.presentation

import com.example.filip.movielist.common.extensions.cancelRequests
import com.example.filip.movielist.common.extensions.getGenres
import com.example.filip.movielist.common.extensions.logSelf
import com.example.filip.movielist.common.utils.NetworkUtils
import com.example.filip.movielist.data.database.DatabaseManager
import com.example.filip.movielist.interaction.MovieDetailsInteractor
import com.example.filip.movielist.model.MovieDetailsResponse
import com.example.filip.movielist.view.MovieDetailsView
import rx.SingleSubscriber

/**
 * Created by Filip Babic @cobe
 */
class MovieDetailsPresenterImpl constructor(private val movieDetailsInteractor: MovieDetailsInteractor, private val databaseManager: DatabaseManager) : MovieDetailsPresenter {

    private var subscriber: SingleSubscriber<MovieDetailsResponse>? = bindMoviesSubscriber()
    private lateinit var movieDetailsView: MovieDetailsView

    private var movie: MovieDetailsResponse? = null
    private var movieID: Int = 0

    override fun getMovie() {
        if (NetworkUtils.isInternetAvailable()) {
            movieDetailsView.getMovieFromNetwork()
        } else {
            movieDetailsView.getMovieFromDatabase()
        }
    }

    override fun setMovieId(movieID: Int) {
        this.movieID = movieID
    }

    override fun handleMovieFavoriteClick() {
        movie?.apply { isFavorite = !isFavorite }
        movieDetailsView.setFloatingButtonStatus(isFavorite = movie?.isFavorite ?: false)
    }

    override fun handleMovieStatus() {
        databaseManager.setMovieFavoriteStatus(movieDetailsResponse = movie)
    }

    override fun handleScrolling(scrollY: Int, isShown: Boolean) {
        if (scrollY > 0 && isShown) {
            movieDetailsView.hideFloatingButton()
        } else {
            movieDetailsView.showFloatingButton()
        }
    }

    override fun requestMovieFromDatabase() {
        movie = databaseManager.getMovieDetailsBy(id = movieID)

        handleMovie(movie)
    }

    override fun requestMovieFromNetwork() {
        movieDetailsInteractor.getMovieBy(movieId = movieID, subscriber = bindMoviesSubscriber())
    }

    override fun cancelSubscriptions() {
        cancelRequests(subscriber)
    }

    override fun onRequestsCancelled() {
        movieDetailsView.onRequestsCancelled()
    }

    override fun setView(view: MovieDetailsView) {
        movieDetailsView = view
    }

    private fun bindMoviesSubscriber(): SingleSubscriber<MovieDetailsResponse> {
        subscriber = subscriber ?: object : SingleSubscriber<MovieDetailsResponse>() {

            override fun onSuccess(value: MovieDetailsResponse?) {
                handleMovie(response = value)
            }

            override fun onError(error: Throwable?) {
                error.logSelf()
                movieDetailsView.showMovieRequestError()
            }
        }

        return subscriber!!
    }

    private fun handleMovie(response: MovieDetailsResponse?) {
        movieDetailsView.setMovieDetails(details = response?.movieDescription ?: "")
        movieDetailsView.setMovieGenres(genres = response?.genreList?.getGenres() ?: "")
        movieDetailsView.setMoviePoster(posterPath = response?.posterURL ?: "")
        movieDetailsView.setMovieReleaseDate(releaseDate = response?.releaseDate ?: "")
        movieDetailsView.setMovieVoteAverage(voteAverage = response?.movieGrade.toString())
        movieDetailsView.setMovieReleaseStatus(releaseStatus = response?.movieStatus ?: "")
        movieDetailsView.setMovieTitle(title = response?.movieTitle ?: "")
        movieDetailsView.setMovieRuntime(runtime = response?.movieRuntime.toString())
    }
}