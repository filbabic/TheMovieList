package com.example.filip.movielist.data.database

import com.example.filip.movielist.common.extensions.deleteItems
import com.example.filip.movielist.common.extensions.save
import com.example.filip.movielist.common.extensions.saveItems
import com.example.filip.movielist.constants.Constants
import com.example.filip.movielist.model.MovieDetailsResponse
import com.example.filip.movielist.model.MovieListModel
import io.realm.Realm

/**
 * Created by Filip Babic @cobe
 */
class DatabaseManagerImpl constructor(private var instance: Realm) : DatabaseManager {

    override fun setMovieFavoriteStatus(movieDetailsResponse: MovieDetailsResponse?) {
        movieDetailsResponse?.save(to = instance)
    }

    override fun getMoviesBy(key: String): List<MovieListModel> {
        return instance.where(MovieListModel::class.java).equalTo(Constants.MOVIE_TYPE_KEY, key).findAll()
    }

    override fun getMovieDetailsBy(id: Int): MovieDetailsResponse {
        val movie: MovieDetailsResponse? = instance.where(MovieDetailsResponse::class.java).equalTo(Constants.REALM_MOVIE_ID_QUERY_KEY, id).findFirst()
        return movie ?: MovieDetailsResponse()
    }

    override fun saveMoviesToDatabase(movies: List<MovieListModel>?) {
        movies?.saveItems(to = instance)
    }

    override fun deleteAllMovies() {
        instance.where(MovieListModel::class.java).findAll().deleteItems(from = instance)
        instance.where(MovieDetailsResponse::class.java).findAll().deleteItems(from = instance)
    }

    override fun isMovieCached(movieId: Int): Boolean {
        return instance.where(MovieDetailsResponse::class.java).equalTo(Constants.REALM_MOVIE_ID_QUERY_KEY, movieId).findFirst() != null
    }

    override fun getFavoriteMovies(): List<MovieDetailsResponse> {
        return instance.copyFromRealm(instance.where(MovieDetailsResponse::class.java).equalTo(Constants.MOVIE_IS_FAVORITE_KEY, true).findAll())
    }

    override fun getAllMovies(): List<MovieListModel> {
        return instance.copyFromRealm(instance.where(MovieListModel::class.java).findAll())
    }

    override fun saveDetails(movieDetailsResponse: MovieDetailsResponse?) {
        movieDetailsResponse?.save(to = instance)
    }
}