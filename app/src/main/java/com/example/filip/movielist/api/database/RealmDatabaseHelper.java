package com.example.filip.movielist.api.database;

import com.example.filip.movielist.pojo.ListMovieItem;
import com.example.filip.movielist.pojo.MovieWrapper;

import java.util.List;

/**
 * Created by Filip on 25/04/2016.
 */
public interface RealmDatabaseHelper {

    List<ListMovieItem> getCachedMovies(String movieType);

    List<ListMovieItem> getAllMovies();

    List<MovieWrapper> getFavoriteMovies();

    void saveMoviesToRealm(List<ListMovieItem> listOfMovies, String movieType);

    MovieWrapper getMovieFromFavorites(long movieId);

    void deleteAllMovies();

    boolean checkIfUserAddedMovieToFavorite(long movieId);

    void saveMovieToFavorite(MovieWrapper movieToSave);

    void removeMovieFromFavorites(long movieId);
}
