package com.example.filip.movielist.data.handler

import com.example.filip.movielist.common.extensions.addKey
import com.example.filip.movielist.model.MovieListResponse
import java.util.*

/**
 * Created by Filip Babic @cobe
 */
class MoviesHandlerImpl : MoviesHandler {

    private var pageIndex: Int = 1
    private var movieKey: String? = ""

    private var dataModel: MovieListResponse? = null

    override fun getMovieType(): String {
        return movieKey ?: ""
    }

    override fun getPage(): Int {
        return pageIndex
    }

    override fun setMovieType(key: String?) {
        movieKey = key
    }

    override fun setPage(page: Int) {
        pageIndex = page
    }

    override fun getData(): MovieListResponse {
        return dataModel ?: MovieListResponse(ArrayList())
    }

    override fun setData(data: MovieListResponse?) {
        data?.results?.addKey(key = getMovieType())

        dataModel = data
    }
}