package com.example.filip.movielist.utils;

import com.example.filip.movielist.BuildConfig;
import com.example.filip.movielist.constants.Constants;
import com.example.filip.movielist.pojo.MovieListModel;
import com.example.filip.movielist.pojo.MovieGenreModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Filip on 01/05/2016.
 */
public class StringUtils {

    public static void logError(Throwable throwable) {
        if (BuildConfig.DEBUG) throwable.printStackTrace();
    }

    public static Map<String, String> createListMoviesQueryMap(int pageToLoad, String apiKey) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(Constants.PAGE_QUERY_KEY, String.valueOf(pageToLoad));
        queryMap.put(Constants.API_QUERY_KEY, apiKey);
        return queryMap;
    }

    public static List<String> getMovieTitlesFromMovieItems(List<MovieListModel> movieItems) {
        List<String> movieTitles = new ArrayList<>();
        for (MovieListModel x : movieItems) {
            movieTitles.add(x.getMovieTitle());
        }
        return movieTitles;
    }

    public static String getMovieKeyFromMovieString(String movieKey) {
        switch (movieKey) {
            case Constants.MOVIE_TYPE_UPCOMING:
                return Constants.MOVIE_TYPE_KEY_UPCOMING;
            case Constants.MOVIE_TYPE_TOP_RATED:
                return Constants.MOVIE_TYPE_KEY_TOP_RATED;
            case Constants.MOVIE_TYPE_POPULAR:
                return Constants.MOVIE_TYPE_KEY_POPULAR;
        }
        return null;
    }

    public static String getGenresString(List<MovieGenreModel> movieGenres) {
        if (movieGenres != null) {
            StringBuilder builder = new StringBuilder();
            for (MovieGenreModel x : movieGenres) {
                String stringToAppend = x.getGenreName() + " ";
                builder.append(stringToAppend);
            }
            return builder.toString();
        } else {
            return "N/A";
        }
    }

    public static boolean stringIsEmptyOrNull(String string) {
        return (string == null || string.isEmpty() || string.trim().isEmpty());
    }
}
