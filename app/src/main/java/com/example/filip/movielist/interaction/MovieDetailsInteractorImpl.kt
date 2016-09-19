package com.example.filip.movielist.interaction

import com.example.filip.movielist.common.extensions.subscribe
import com.example.filip.movielist.constants.Constants
import com.example.filip.movielist.model.MovieDetailsResponse
import com.example.filip.movielist.network.MovieDetailsApiService
import com.example.filip.movielist.scheduler.SchedulerManager
import rx.SingleSubscriber

/**
 * Created by Filip Babic @cobe
 */
class MovieDetailsInteractorImpl constructor(private val movieDetailsApiService: MovieDetailsApiService, private val manager: SchedulerManager) : MovieDetailsInteractor {

    override fun getMovieBy(movieId: Int, subscriber: SingleSubscriber<MovieDetailsResponse>) {
        movieDetailsApiService.getMovieBy(movieId, Constants.API_KEY).subscribe(to = manager, with = subscriber)
    }
}