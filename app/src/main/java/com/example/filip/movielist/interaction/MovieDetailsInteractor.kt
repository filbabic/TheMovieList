package com.example.filip.movielist.interaction

import com.example.filip.movielist.model.MovieDetailsResponse
import rx.SingleSubscriber

/**
 * Created by Filip Babic @cobe
 */
interface MovieDetailsInteractor : BaseInteractor {

    fun getMovieBy(movieId: Int, subscriber: SingleSubscriber<MovieDetailsResponse>?)
}