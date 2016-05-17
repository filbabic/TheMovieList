package com.example.filip.movielist.ui.movie.presenter;


/**
 * Created by Filip on 01/05/2016.
 */
public interface DisplayMoviePresenter {
    void requestMovieFromNetwork(long movieId);

    void requestMovieFromDatabase(long movieId);

    boolean checkIfMovieIsCached(long movieId);

    void addMovieToFavorites();

    void removeMovieFromFavorites(long movieId);
}
