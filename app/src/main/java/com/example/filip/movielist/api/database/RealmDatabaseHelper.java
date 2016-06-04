package com.example.filip.movielist.api.database;

import com.example.filip.movielist.pojo.MovieListModel;
import com.example.filip.movielist.pojo.MovieDetails;

import java.util.List;

/**
 * Created by Filip on 25/04/2016.
 */
public interface RealmDatabaseHelper {

    List<MovieListModel> getCachedMovies(String movieType);

    List<MovieListModel> getAllMovies();

    List<MovieDetails> getFavoriteMovies();

    void saveMoviesToRealm(List<MovieListModel> listOfMovies, String movieType);

    MovieDetails getMovieFromFavorites(int movieId);

    void deleteAllMovies();

    boolean checkIfUserAddedMovieToFavorite(int movieId);

    void saveMovieToFavorite(MovieDetails movieToSave);

    void removeMovieFromFavorites(int movieId);
}
