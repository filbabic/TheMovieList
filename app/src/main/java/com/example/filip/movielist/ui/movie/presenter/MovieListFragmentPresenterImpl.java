package com.example.filip.movielist.ui.movie.presenter;

import com.example.filip.movielist.api.network.NetworkingHelper;
import com.example.filip.movielist.api.ResponseListener;
import com.example.filip.movielist.api.database.RealmDatabaseHelper;
import com.example.filip.movielist.pojo.ListMovieItem;
import com.example.filip.movielist.ui.movie.view.MovieView;
import com.example.filip.movielist.utils.StringUtils;

import java.util.List;

/**
 * Created by Filip on 01/05/2016.
 */
public class MovieListFragmentPresenterImpl implements MovieListFragmentPresenter {
    private final MovieView mMovieView;
    private final NetworkingHelper mNetworkingHelper;
    private final RealmDatabaseHelper mRealmDatabaseHelper;
    private final String mMovieTypeKey;

    public MovieListFragmentPresenterImpl(MovieView mMovieView, NetworkingHelper mNetworkingHelper, RealmDatabaseHelper mRealmDatabaseHelper, String mMovieTypeKey) {
        this.mMovieView = mMovieView;
        this.mNetworkingHelper = mNetworkingHelper;
        this.mRealmDatabaseHelper = mRealmDatabaseHelper;
        this.mMovieTypeKey = mMovieTypeKey;
    }

    @Override
    public void requestMoviesFromNetwork(int whichPageToLoad) {
        mNetworkingHelper.getListOfMoviesForSelectedCategory(mMovieTypeKey, whichPageToLoad, new ResponseListener<List<ListMovieItem>>() {
            @Override
            public void onSuccess(List<ListMovieItem> callback) {
                mMovieView.loadAdapterWithItems(callback);
            }

            @Override
            public void onFailure(Throwable throwable) {
                StringUtils.logError(throwable);
            }
        });
    }

    @Override
    public void storeMoviesInDatabase(List<ListMovieItem> mListOfMoviesToStore) {
        mRealmDatabaseHelper.saveMoviesToRealm(mListOfMoviesToStore, mMovieTypeKey);
    }

    @Override
    public void loadMoviesFromDatabase() {
        List<ListMovieItem> cachedMovies = mRealmDatabaseHelper.getCachedMovies(mMovieTypeKey);
        if (cachedMovies != null && cachedMovies.size() != 0)
            mMovieView.loadCachedMovies(mRealmDatabaseHelper.getCachedMovies(mMovieTypeKey));
        else mMovieView.onFailure();
    }
}
