package com.example.filip.movielist.ui.movie.view;

import com.example.filip.movielist.pojo.MovieListModel;

import java.util.List;

/**
 * Created by Filip on 25/04/2016.
 */
public interface MovieView {
    void setAdapterItems(List<MovieListModel> mDataSource);

    void addMoreItemsToAdapter(List<MovieListModel> mDataSource);

    void requestMovieFromNetwork();

    void requestMovieFromDatabase();

    void setListIsRefreshing(boolean isRefreshing);
}
