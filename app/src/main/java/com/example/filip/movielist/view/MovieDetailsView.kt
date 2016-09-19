package com.example.filip.movielist.view

/**
 * Created by Filip Babic @cobe
 */
interface MovieDetailsView : BaseView {

    fun setMoviePoster(posterPath: String)

    fun setMovieTitle(title: String)

    fun setMovieDetails(details: String)

    fun setMovieGenres(genres: String)

    fun setMovieReleaseDate(releaseDate: String)

    fun setMovieRuntime(runtime: String)

    fun setMovieRevenue(revenue: String)

    fun setMovieVoteAverage(voteAverage: String)

    fun setMovieReleaseStatus(releaseStatus: String)

    fun getMovieFromNetwork()

    fun getMovieFromDatabase()

    fun setFloatingButtonStatus(isFavorite: Boolean)

    fun showFloatingButton()

    fun hideFloatingButton()

    fun showMovieRequestError()
}