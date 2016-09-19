package com.example.filip.movielist.network

import com.example.filip.movielist.model.MovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Single

/**
 * Created by Filip Babic @cobe
 */
interface MovieDetailsApiService {

    @GET("/3/movie/{movieID}")
    fun getMovieBy(@Path("movieID") movieID: Int, @Query("api_key") apiKey: String): Single<MovieDetailsResponse>
}