package com.example.filip.movielist.ui.favorite.presenter;

import com.example.filip.movielist.api.database.RealmDatabaseHelper;
import com.example.filip.movielist.pojo.ListMovieItem;
import com.example.filip.movielist.pojo.MovieWrapper;
import com.example.filip.movielist.ui.favorite.view.FavoriteMoviesView;
import com.example.filip.movielist.utils.DataUtils;

import java.util.List;

/**
 * Created by Filip on 04/05/2016.
 */
public class FavoriteMoviesPresenterImpl implements FavoriteMoviesPresenter {
    private final FavoriteMoviesView favoriteMoviesView;
    private final RealmDatabaseHelper databaseHelper;

    public FavoriteMoviesPresenterImpl(FavoriteMoviesView favoriteMoviesView, RealmDatabaseHelper databaseHelper) {
        this.favoriteMoviesView = favoriteMoviesView;
        this.databaseHelper = databaseHelper;
    }

    @Override
    public void requestMoviesForView() {
        List<MovieWrapper> movieWrappers = databaseHelper.getFavoriteMovies();
        if (movieWrappers != null && movieWrappers.size() != 0) {
            List<ListMovieItem> items = DataUtils.getFavoriteMovieListItems(movieWrappers);
            favoriteMoviesView.loadFavoriteMoviesIntoAdapter(items);
        } else favoriteMoviesView.onFailedToLoadFavoriteMoviesFromDatabase();
    }
}
