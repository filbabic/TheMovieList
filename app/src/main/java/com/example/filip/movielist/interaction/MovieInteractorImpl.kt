package com.example.filip.movielist.interaction

import com.example.filip.movielist.common.extensions.cancelSubscriptions
import com.example.filip.movielist.common.extensions.subscribe
import com.example.filip.movielist.common.extensions.toKey
import com.example.filip.movielist.common.utils.QueryUtils
import com.example.filip.movielist.constants.Constants
import com.example.filip.movielist.model.MovieListResponse
import com.example.filip.movielist.network.MovieApiService
import com.example.filip.movielist.scheduler.SchedulerManager
import rx.SingleSubscriber

/**
 * Created by Filip Babic @cobe
 */
class MovieInteractorImpl constructor(private val movieService: MovieApiService, private val manager: SchedulerManager) : MoviesInteractor {

    override fun getMoviesBy(movieType: String, page: Int, subscriber: SingleSubscriber<MovieListResponse>?) {

        movieService.getMoviesBy(movieType = movieType.toKey(), queryMap = QueryUtils.createMoviesQueryMap(pageToLoad = page, apiKey = Constants.API_KEY))
                .subscribe(to = manager, with = subscriber)
    }

    override fun cancelRequest(subscribers: Array<SingleSubscriber<out Any>?>?) {
        cancelSubscriptions(subscribers = subscribers)
    }
}