package com.example.filip.movielist.interaction

import com.example.filip.movielist.model.MovieListResponse
import rx.SingleSubscriber

/**
 * Created by Filip Babic @cobe
 */
interface MoviesInteractor : BaseInteractor {

    fun getMoviesBy(movieType: String, page: Int, subscriber: SingleSubscriber<MovieListResponse>?)
}