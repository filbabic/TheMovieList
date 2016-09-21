package com.example.filip.movielist.data.handler

import com.example.filip.movielist.common.extensions.getGenres
import com.example.filip.movielist.model.MovieDetailsResponse

/**
 * Created by Filip Babic @cobe
 */

class MovieDetailsHandlerImpl : MovieDetailsHandler {

    private var dataModel: MovieDetailsResponse? = null

    override fun getDescription(): String {
        return dataModel?.movieDescription ?: ""
    }

    override fun getGenres(): String {
        return dataModel?.genreList?.getGenres() ?: ""
    }

    override fun getPosterPath(): String {
        return dataModel?.posterURL ?: ""
    }

    override fun getReleaseDate(): String {
        return dataModel?.releaseDate ?: ""
    }

    override fun getRuntime(): String {
        return dataModel?.movieRuntime.toString()
    }

    override fun getStatus(): String {
        return dataModel?.movieStatus ?: ""
    }

    override fun getTitle(): String {
        return dataModel?.movieTitle ?: ""
    }

    override fun getVoteAverage(): String {
        return dataModel?.movieGrade.toString()
    }

    override fun getRevenue(): String {
        return dataModel?.movieRevenue.toString()
    }

    override fun getFavoriteStatus(): Boolean {
        return dataModel?.isFavorite ?: false
    }

    override fun changeFavoriteStatus() {
        dataModel?.apply { isFavorite = !isFavorite }
    }

    override fun setData(data: MovieDetailsResponse?) {
        dataModel = data
    }

    override fun getData(): MovieDetailsResponse? {
        return dataModel
    }
}