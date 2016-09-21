package com.example.filip.movielist.data.handler

import com.example.filip.movielist.model.MovieListResponse

/**
 * Created by Filip Babic @cobe
 */
interface MoviesHandler : DataHandler<MovieListResponse> {

    fun setMovieType(key: String?)

    fun getMovieType(): String

    fun setPage(page: Int)

    fun getPage(): Int
}