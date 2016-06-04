package com.example.filip.movielist.ui.database.presenter;

import com.example.filip.movielist.R;
import com.example.filip.movielist.api.database.RealmDatabaseHelper;
import com.example.filip.movielist.pojo.MovieListModel;
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
        List<MovieListModel> items = realmDatabaseHelper.getAllMovies();
        if (items != null && items.size() != 0) { //if realm results are null/empty
            List<String> mMovieTitles = StringUtils.getMovieTitlesFromMovieItems(items);
            databaseActivityView.setNumberOfCachedMovies(mMovieTitles.size());
            databaseActivityView.setAdapterItems(mMovieTitles);
        } else {
            databaseActivityView.onFailedToGetCachedMoviesFromDatabase();
            databaseActivityView.setNumberOfCachedMovies(0);
        }
    }

    @Override
    public void deleteMoviesFromRealm() {
        realmDatabaseHelper.deleteAllMovies();
        databaseActivityView.clearItemsFromAdapter();
        databaseActivityView.setNumberOfCachedMovies(0);
    }

    @Override
    public void handleUserClickedMenuItem(int itemID) {
        if (itemID == R.id.activity_database_menu_delete_cached_movies_action) {
            databaseActivityView.showDeleteDialog();
        }
    }
}
