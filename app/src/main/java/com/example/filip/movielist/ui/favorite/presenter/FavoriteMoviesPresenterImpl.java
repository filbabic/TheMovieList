package com.example.filip.movielist.ui.favorite.presenter;

import com.example.filip.movielist.api.database.RealmDatabaseHelper;
import com.example.filip.movielist.pojo.MovieListModel;
import com.example.filip.movielist.pojo.MovieDetails;
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
        List<MovieDetails> movieDetailsList = databaseHelper.getFavoriteMovies();
        if (movieDetailsList != null && movieDetailsList.size() != 0) {
            List<MovieListModel> items = DataUtils.createFavoriteMoviesListItemsFromCachedMovieDetails(movieDetailsList);
            favoriteMoviesView.setAdapterItems(items);
        } else favoriteMoviesView.onFailedToLoadFavoriteMoviesFromDatabase();
    }
}
