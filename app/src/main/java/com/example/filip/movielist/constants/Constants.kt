package com.example.filip.movielist.constants

/**
 * Created by Filip Babic @cobe
 */
class Constants {

    companion object {
        const val API_KEY = "25937d0ea465028585770e26d6b378a2"
        const val IMAGE_URL = "http://image.tmdb.org/t/p/original"
        const val WEB_VIEW_BASE_URL = "https://www.themoviedb.org/"

        const val MOVIE_POSTER_FORMAT = "$IMAGE_URL%1\$s"
        const val PAGE_QUERY_KEY = "page"
        const val API_QUERY_KEY = "api_key"

        const val MOVIE_IS_FAVORITE_KEY = "isFavorite"
        const val MOVIE_ID_KEY = "id"
        const val MOVIE_TYPE_KEY = "movieType"

        const val MOVIE_TYPE_UPCOMING = "UPCOMING"
        const val MOVIE_TYPE_TOP_RATED = "TOP RATED"
        const val MOVIE_TYPE_POPULAR = "POPULAR"
        const val MOVIE_TYPE_KEY_TOP_RATED = "top_rated"
        const val REALM_MOVIE_ID_QUERY_KEY = "movieId"

        const val TRANSITION_NAME = "poster"

        const val SHARED_PREFERENCES = "movie_list_prefs"
        const val DEFAULT_USERNAME = "Guest"
        const val PREFS_KEY_USERNAME = "username"
    }
}