package com.example.filip.movielist.ui.movie.presenter;

import com.example.filip.movielist.pojo.ListMovieItem;

import java.util.List;

/**
 * Created by Filip on 01/05/2016.
 */
public interface MovieListFragmentPresenter {
    void requestMoviesFromNetwork(int whichPageToLoad);

    void storeMoviesInDatabase(List<ListMovieItem> mListOfMoviesToStore);

    void loadMoviesFromDatabase();
}
