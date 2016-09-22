package com.example.filip.movielist.common.extensions

import com.example.filip.movielist.model.MovieDetailsResponse
import com.example.filip.movielist.model.MovieGenre
import com.example.filip.movielist.model.MovieListModel
import java.util.*

/**
 * Created by Filip Babic @cobe
 */

fun List<MovieListModel>.addKey(key: String): List<MovieListModel>? {
    return map { it.apply { movieType = key } }
}

fun <T> List<T>?.isValid(): Boolean {
    return !(this?.isEmpty() ?: false)
}

fun <T> MutableList<T>.fill(with: List<T>?, reset: Boolean) {
    if (with.isValid()) {
        if (reset) {
            clear()
        }
        addAll(with!!)
    }
}

fun List<MovieDetailsResponse>.transformToMovieListModels(): MutableList<MovieListModel>? {
    val movies: MutableList<MovieListModel> = ArrayList()
    forEach { movies.add(MovieListModel(it.posterURL, it.movieId, it.movieTitle, it.movieDescription, it.releaseDate, it.movieGrade)) }
    return movies
}

fun List<MovieListModel>.getMoviesTitles(): List<String> {
    val movieTitles: MutableList<String> = ArrayList()
    forEach { movieTitles.add(it.movieTitle) }
    return movieTitles
}

fun List<MovieGenre>.getGenres(): String {
    val builder = StringBuilder()

    forEach { builder.append(it.genre).append(" ") }
    return builder.toString()
}