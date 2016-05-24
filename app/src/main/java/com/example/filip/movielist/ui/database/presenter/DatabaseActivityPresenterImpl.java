package com.example.filip.movielist.ui.database.presenter;

import com.example.filip.movielist.api.database.RealmDatabaseHelper;
import com.example.filip.movielist.pojo.ListMovieItem;
import com.example.filip.movielist.ui.database.view.DatabaseActivityView;
import com.example.filip.movielist.utils.StringUtils;

import java.util.List;

/**
 * Created by Filip on 03/05/2016.
 */
public class DatabaseActivityPresenterImpl implements DatabaseActivityPresenter {
    private final RealmDatabaseHelper realmDatabaseHelper;
    private final DatabaseActivityView databaseActivityView;

    public DatabaseActivityPresenterImpl(RealmDatabaseHelper realmDatabaseHelper, DatabaseActivityView databaseActivityView) {
        this.realmDatabaseHelper = realmDatabaseHelper;
        this.databaseActivityView = databaseActivityView;
    }

    @Override
    public void requestMoviesFromRealm() {
        List<ListMovieItem> items = realmDatabaseHelper.getAllMovies();
        if (items != null && items.size() != 0) {
            List<String> mMovieTitles = StringUtils.getMovieTitlesFromMovieItems(items);
            databaseActivityView.fillAdapterWithItems(mMovieTitles);
        } else databaseActivityView.onFailedToLoadCachedMoviesFromDatabase();
    }

    @Override
    public void deleteMoviesFromRealm() {
        realmDatabaseHelper.deleteAllMovies();
        databaseActivityView.clearItemsFromAdapter();
    }

    @Override
    public void handleUserClickedMenuItem() {
        databaseActivityView.showDeleteDialog();
    }
}
