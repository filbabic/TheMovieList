package com.example.filip.movielist.ui.movie.view;

import com.example.filip.movielist.pojo.MovieListModel;

import java.util.List;

/**
 * Created by Filip on 25/04/2016.
 */
public interface MovieView {
    void addItemsToAdapter(List<MovieListModel> mDataSource);

    void requestMovieFromNetwork();

    void requestMovieFromDatabase();
}
