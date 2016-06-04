package com.example.filip.movielist.ui.favorite.view;

import com.example.filip.movielist.pojo.MovieListModel;

import java.util.List;

/**
 * Created by Filip on 03/05/2016.
 */
public interface FavoriteMoviesView {
    void setAdapterItems(List<MovieListModel> moviesToLoad);

    void onFailedToLoadFavoriteMoviesFromDatabase();
}
