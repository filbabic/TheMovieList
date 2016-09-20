package com.example.filip.movielist.data.handler

import com.example.filip.movielist.model.MovieDetailsResponse

/**
 * Created by Filip Babic @cobe
 */
interface MovieDetailsHandler : DataHandler<MovieDetailsResponse?> {

    fun getTitle(): String

    fun getDescription(): String

    fun getReleaseDate(): String

    fun getVoteAverage(): String

    fun getGenres(): String

    fun getPosterPath(): String

    fun getStatus(): String

    fun getRuntime(): String

    fun changeFavoriteStatus()

    fun getRevenue(): String
}