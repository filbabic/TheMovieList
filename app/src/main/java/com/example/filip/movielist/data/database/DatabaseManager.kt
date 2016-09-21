package com.example.filip.movielist.data.database

import com.example.filip.movielist.model.MovieDetailsResponse
import com.example.filip.movielist.model.MovieListModel

/**
 * Created by Filip Babic @cobe
 */
interface DatabaseManager {

    fun saveMoviesToDatabase(movies: List<MovieListModel>?)

    fun saveDetails(movieDetailsResponse: MovieDetailsResponse?)

    fun setMovieFavoriteStatus(movieDetailsResponse: MovieDetailsResponse?)

    fun getMoviesBy(key: String): List<MovieListModel>

    fun getMovieDetailsBy(id: Int): MovieDetailsResponse

    fun getFavoriteMovies(): List<MovieDetailsResponse>

    fun getAllMovies(): List<MovieListModel>

    fun deleteAllMovies()

    fun isMovieCached(movieId: Int): Boolean
}