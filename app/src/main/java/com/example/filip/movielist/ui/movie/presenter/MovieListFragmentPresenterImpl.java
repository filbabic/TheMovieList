package com.example.filip.movielist.ui.movie.presenter;

import com.example.filip.movielist.App;
import com.example.filip.movielist.api.network.NetworkingHelper;
import com.example.filip.movielist.api.ResponseListener;
import com.example.filip.movielist.api.database.RealmDatabaseHelper;
import com.example.filip.movielist.pojo.MovieDetails;
import com.example.filip.movielist.pojo.MovieListModel;
import com.example.filip.movielist.ui.movie.view.MovieView;
import com.example.filip.movielist.utils.ConnectionUtils;
import com.example.filip.movielist.utils.DataUtils;
import com.example.filip.movielist.utils.StringUtils;

import java.util.List;

/**
 * Created by Filip on 01/05/2016.
 */
public class MovieListFragmentPresenterImpl implements MovieListFragmentPresenter {
    private final MovieView mMovieView;
    private final NetworkingHelper mNetworkingHelper;
    private final RealmDatabaseHelper mRealmDatabaseHelper;
    private String mMovieTypeKey;
    private int whichPageToLoad;

    public MovieListFragmentPresenterImpl(MovieView mMovieView, NetworkingHelper mNetworkingHelper, RealmDatabaseHelper mRealmDatabaseHelper) {
        this.mMovieView = mMovieView;
        this.mNetworkingHelper = mNetworkingHelper;
        this.mRealmDatabaseHelper = mRealmDatabaseHelper;
        this.whichPageToLoad = 1;
    }

    @Override
    public void requestMoviesFromNetwork() {
        mNetworkingHelper.getListOfMoviesForSelectedCategory(mMovieTypeKey, whichPageToLoad, new ResponseListener<List<MovieListModel>>() {
            @Override
            public void onSuccess(List<MovieListModel> callback) {
                if (whichPageToLoad == 1) {
                    mMovieView.setAdapterItems(callback);
                    mRealmDatabaseHelper.saveMoviesToRealm(callback); //fully cache only the first page, to have less of an impact on memory
                    List<MovieDetails> movieDetailsList = DataUtils.createListOfDetailsMoviesFromMovieListModels(callback, mMovieTypeKey);
                    mRealmDatabaseHelper.saveMovieDetailsForFirstPage(movieDetailsList);
                } else if (whichPageToLoad > 1) {
                    mMovieView.addMoreItemsToAdapter(callback);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                StringUtils.logError(throwable);
            }
        });
    }

    @Override
    public void setMovieTypeKey(String movieTypeKey) {
        if (!StringUtils.stringIsEmptyOrNull(movieTypeKey)) {
            this.mMovieTypeKey = movieTypeKey;
        }
    }

    @Override
    public void requestMoviesFromDatabase() {
        List<MovieListModel> cachedMovies = mRealmDatabaseHelper.getCachedMoviesByMovieType(mMovieTypeKey);
        if (cachedMovies != null && cachedMovies.size() != 0) {
            mMovieView.setAdapterItems(cachedMovies);
        }
        mMovieView.requestMovieFromNetwork(); //also always loads the first page in case of any changes
    }

    @Override
    public void getMoviesToDisplay() {
        if (whichPageToLoad > 1 && ConnectionUtils.checkIfInternetConnectionIsAvailable(App.get())) {
            mMovieView.requestMovieFromNetwork();
        } else if (whichPageToLoad == 1) {
            mMovieView.requestMovieFromDatabase();
        }
        mMovieView.setListIsRefreshing(false);
    }

    @Override
    public void attemptToLoadMoreItemsFromNetworkService() {
        if (ConnectionUtils.checkIfInternetConnectionIsAvailable(App.get())) {
            this.whichPageToLoad++;
            mMovieView.requestMovieFromNetwork();
        }
    }
}
