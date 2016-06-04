package com.example.filip.movielist.utils;

import android.os.Bundle;

import com.example.filip.movielist.constants.Constants;
import com.example.filip.movielist.pojo.MovieListModel;
import com.example.filip.movielist.pojo.ListOfMovies;
import com.example.filip.movielist.pojo.MovieDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 25/04/2016.
 */
public class DataUtils {

    public static Bundle createBundle(String movieType) {
        Bundle bundleToAdd = new Bundle();
        bundleToAdd.putString(Constants.MOVIE_TYPE_KEY, movieType);
        return bundleToAdd;
    }

    public static List<MovieListModel> addMovieTypeKey(ListOfMovies moviesToAddTo, String movieType) {
        List<MovieListModel> movieItems = moviesToAddTo.getResults();
        for (MovieListModel current : movieItems) {
            current.setMovieType(movieType);
        }
        return movieItems;
    }

    public static List<MovieListModel> createFavoriteMoviesListItemsFromCachedMovieDetails(List<MovieDetails> movieWrappers) {
        List<MovieListModel> movieListModels = new ArrayList<>();
        for (MovieDetails x : movieWrappers) {
            MovieListModel item = new MovieListModel(x.getPosterURL(), x.getMovieId(), x.getMovieTitle(), x.getMovieDescription(), x.getReleaseDate());
            movieListModels.add(item);
        }
        return movieListModels;
    }

    public static List<MovieListModel> filterMoviesThatAreNotAlreadyCached(List<MovieListModel> receivedMovies, List<MovieListModel> cachedMovies) {
        List<MovieListModel> notCachedMovies = new ArrayList<>();
        for (MovieListModel x : receivedMovies) {
            boolean isCached = false;
            for (int i = 0; i < (cachedMovies.size() - 1); i++) {
                MovieListModel cachedMovie = cachedMovies.get(i);
                if (cachedMovie.getMovieID() == x.getMovieID()) {
                    isCached = true;
                    break;
                }
            }
            if (!isCached) {
                notCachedMovies.add(x);
            }
        }
        return notCachedMovies;
    }
}
