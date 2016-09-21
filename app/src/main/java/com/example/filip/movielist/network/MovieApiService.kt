package com.example.filip.movielist.network

import com.example.filip.movielist.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import rx.Single

/**
 * Created by Filip Babic @cobe
 */
interface MovieApiService {

    @GET("/3/movie/{movieType}")
    fun getMoviesBy(@Path("movieType") movieType: String, @QueryMap queryMap: Map<String, String>): Single<MovieListResponse>
}