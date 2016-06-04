package com.example.filip.movielist.api.database;

import com.example.filip.movielist.constants.Constants;
import com.example.filip.movielist.pojo.MovieListModel;
import com.example.filip.movielist.pojo.MovieDetails;
import com.example.filip.movielist.utils.DataUtils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Filip on 25/04/2016.
 */
public class RealmDatabaseHelperImpl implements RealmDatabaseHelper {
    private final Realm mRealmInstance;

    public RealmDatabaseHelperImpl(Realm mRealmInstance) {
        this.mRealmInstance = mRealmInstance;
    }

    @Override
    public List<MovieListModel> getCachedMovies(String movieType) {
        return mRealmInstance.where(MovieListModel.class).contains(Constants.MOVIE_TYPE_KEY, movieType).findAll();
    }

    @Override
    public void saveMoviesToRealm(List<MovieListModel> listOfMovies, String movieType) {
        if (listOfMovies != null) {
            RealmResults<MovieListModel> cachedMovies = mRealmInstance.allObjects(MovieListModel.class);
            List<MovieListModel> filteredMovies = DataUtils.filterMoviesThatAreNotAlreadyCached(listOfMovies, cachedMovies);
            saveMoviesIntoRealm(filteredMovies);
        }
    }

    @Override
    public MovieDetails getMovieFromFavorites(int movieId) {
        return mRealmInstance.where(MovieDetails.class).equalTo(Constants.REALM_MOVIE_ID_QUERY_KEY, movieId).findFirst();
    }

    @Override
    public List<MovieListModel> getAllMovies() {
        return mRealmInstance.allObjects(MovieListModel.class);
    }

    @Override
    public List<MovieDetails> getFavoriteMovies() {
        return mRealmInstance.allObjects(MovieDetails.class);
    }

    @Override
    public void deleteAllMovies() {
        mRealmInstance.beginTransaction();
        mRealmInstance.allObjects(MovieListModel.class).deleteAllFromRealm();
        mRealmInstance.commitTransaction();
    }

    @Override
    public boolean checkIfUserAddedMovieToFavorite(int movieId) {
        MovieDetails favoriteMovie = mRealmInstance.where(MovieDetails.class).equalTo(Constants.REALM_MOVIE_ID_QUERY_KEY, movieId).findFirst();
        return (favoriteMovie != null);
    }

    @Override
    public void saveMovieToFavorite(MovieDetails movieToSave) {
        if (movieToSave != null) {
            mRealmInstance.beginTransaction();
            mRealmInstance.copyToRealm(movieToSave);
            mRealmInstance.commitTransaction();
        }
    }

    @Override
    public void removeMovieFromFavorites(int movieId) {
        MovieDetails movieToDelete = mRealmInstance.where(MovieDetails.class).equalTo(Constants.REALM_MOVIE_ID_QUERY_KEY, movieId).findFirst();
        if (movieToDelete != null) {
            mRealmInstance.beginTransaction();
            movieToDelete.deleteFromRealm();
            mRealmInstance.commitTransaction();
        }
    }

    protected void saveMoviesIntoRealm(List<MovieListModel> listOfMovies) {
        if (listOfMovies != null && listOfMovies.size() != 0) {
            mRealmInstance.beginTransaction();
            mRealmInstance.copyToRealm(listOfMovies);
            mRealmInstance.commitTransaction();
        }
    }
}
