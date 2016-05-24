package com.example.filip.movielist.api.database;

import com.example.filip.movielist.constants.Constants;
import com.example.filip.movielist.pojo.ListMovieItem;
import com.example.filip.movielist.pojo.MovieWrapper;

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
    public List<ListMovieItem> getCachedMovies(String movieType) {
        return mRealmInstance.where(ListMovieItem.class).contains(Constants.MOVIE_TYPE_KEY, movieType).findAll();
    }

    @Override
    public void saveMoviesToRealm(List<ListMovieItem> listOfMovies, String movieType) {
        mRealmInstance.beginTransaction();
        RealmResults<ListMovieItem> cachedMovies = mRealmInstance.where(ListMovieItem.class).contains(Constants.MOVIE_TYPE_KEY, movieType).findAll();
        cachedMovies.deleteAllFromRealm();
        mRealmInstance.copyToRealm(listOfMovies);
        mRealmInstance.commitTransaction();
    }

    @Override
    public MovieWrapper getMovieFromFavorites(long movieId) {
        return mRealmInstance.where(MovieWrapper.class).equalTo(Constants.REALM_MOVIE_ID_QUERY_KEY, movieId).findFirst();
    }

    @Override
    public List<ListMovieItem> getAllMovies() {
        return mRealmInstance.allObjects(ListMovieItem.class);
    }

    @Override
    public List<MovieWrapper> getFavoriteMovies() {
        return mRealmInstance.allObjects(MovieWrapper.class);
    }

    @Override
    public void deleteAllMovies() {
        mRealmInstance.beginTransaction();
        mRealmInstance.allObjects(ListMovieItem.class).deleteAllFromRealm();
        mRealmInstance.commitTransaction();
    }

    @Override
    public boolean checkIfUserAddedMovieToFavorite(long movieId) {
        MovieWrapper favoriteMovie = mRealmInstance.where(MovieWrapper.class).equalTo(Constants.REALM_MOVIE_ID_QUERY_KEY, movieId).findFirst();
        return (favoriteMovie != null);
    }

    @Override
    public void saveMovieToFavorite(MovieWrapper movieToSave) {
        mRealmInstance.beginTransaction();
        mRealmInstance.copyToRealm(movieToSave);
        mRealmInstance.commitTransaction();
    }

    @Override
    public void removeMovieFromFavorites(long movieId) {
        MovieWrapper movieToDelete = mRealmInstance.where(MovieWrapper.class).equalTo(Constants.REALM_MOVIE_ID_QUERY_KEY, movieId).findFirst();
        if (movieToDelete != null) {
            mRealmInstance.beginTransaction();
            movieToDelete.deleteFromRealm();
            mRealmInstance.commitTransaction();
        }
    }
}
