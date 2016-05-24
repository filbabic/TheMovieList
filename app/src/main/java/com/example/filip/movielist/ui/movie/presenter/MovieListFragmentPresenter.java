package com.example.filip.movielist.ui.movie.presenter;

/**
 * Created by Filip on 01/05/2016.
 */
public interface MovieListFragmentPresenter {
    void requestMoviesFromNetwork(int whichPageToLoad);

    void setMovieTypeKey(String movieTypeKey);

    void requestMoviesFromDatabase();
}
