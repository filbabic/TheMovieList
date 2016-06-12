package com.example.filip.movielist.api.database;

import com.example.filip.movielist.pojo.MovieListModel;
import com.example.filip.movielist.pojo.MovieDetails;

import java.util.List;

/**
 * Created by Filip on 25/04/2016.
 */
public interface RealmDatabaseHelper {

    List<MovieListModel> getCachedMoviesByMovieType(String movieType);

    List<MovieListModel> getAllMovies();

    List<MovieDetails> getFavoriteMovies();

    void saveMoviesToRealm(List<MovieListModel> listOfMovies, String movieType);

    MovieDetails getMovieByIdIfCached(int movieId);

    void deleteAllMovies();

    boolean checkIfMovieIsCached(int movieId);

    void saveMovieToFavorite(MovieDetails movieToSave);

    void removeMovieFromFavorites(int movieId);

    void saveMovieDetailsForFirstPage(List<MovieDetails> movieDetailsList);

    void updateMovieDetails(MovieDetails movieDetails);
}
