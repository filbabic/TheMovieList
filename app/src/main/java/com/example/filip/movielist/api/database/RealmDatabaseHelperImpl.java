package com.example.filip.movielist.api.database;

import com.example.filip.movielist.constants.Constants;
import com.example.filip.movielist.pojo.MovieListModel;
import com.example.filip.movielist.pojo.MovieDetails;
import com.example.filip.movielist.utils.DataUtils;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Filip on 25/04/2016.
 */
public class RealmDatabaseHelperImpl implements RealmDatabaseHelper {
    private final Realm mRealmInstance;

    public RealmDatabaseHelperImpl(Realm mRealmInstance) {
        this.mRealmInstance = mRealmInstance;
    }

    @Override
    public List<MovieListModel> getCachedMoviesByMovieType(String movieType) {
        return DataUtils.createMovieListModelCopiesFromRealmObjects(mRealmInstance.where(MovieListModel.class).contains(Constants.MOVIE_TYPE_KEY, movieType).findAll());
    }

    @Override
    public void saveMoviesToRealm(List<MovieListModel> listOfMovies) {
        mRealmInstance.beginTransaction();
        mRealmInstance.copyToRealmOrUpdate(listOfMovies);
        mRealmInstance.commitTransaction();
    }

    @Override
    public MovieDetails getMovieByIdIfCached(int movieId) {
        MovieDetails movieDetails = mRealmInstance.where(MovieDetails.class).equalTo(Constants.REALM_MOVIE_ID_QUERY_KEY, movieId).findFirst();
        MovieDetails copy = new MovieDetails(movieDetails.getGenreList(), movieDetails.getMovieId(), movieDetails.getMovieTitle(), movieDetails.getMovieDescription(), movieDetails.getPosterURL(), movieDetails.getReleaseDate(), movieDetails.getMovieRevenue(), movieDetails.getMovieRuntime(), movieDetails.getMovieGrade(), movieDetails.getMovieStatus());
        copy.setFavorite(movieDetails.isFavorite());
        return copy;
    }

    @Override
    public List<MovieListModel> getAllMovies() {
        return mRealmInstance.where(MovieListModel.class).findAll();
    }

    @Override
    public List<MovieDetails> getFavoriteMovies() {
        return DataUtils.createMovieDetailsRealmObjectCopies(mRealmInstance.where(MovieDetails.class).equalTo(Constants.MOVIE_IS_FAVORITE_KEY, true).findAll());
    }

    @Override
    public void deleteAllMovies() {
        mRealmInstance.beginTransaction();
        mRealmInstance.where(MovieListModel.class).findAll().deleteAllFromRealm();
        mRealmInstance.where(MovieDetails.class).findAll().deleteAllFromRealm();
        mRealmInstance.commitTransaction();
    }

    @Override
    public boolean checkIfMovieIsCached(int movieId) {
        MovieDetails favoriteMovie = mRealmInstance.where(MovieDetails.class).equalTo(Constants.REALM_MOVIE_ID_QUERY_KEY, movieId).findFirst();
        return (favoriteMovie != null);
    }

    @Override
    public void saveMovieToFavorite(MovieDetails movieToSave) {
        if (movieToSave != null) {
            movieToSave.setFavorite(true);
            mRealmInstance.beginTransaction();
            mRealmInstance.copyToRealmOrUpdate(movieToSave);
            mRealmInstance.commitTransaction();
        }
    }

    @Override
    public void removeMovieFromFavorites(int movieId) {
        mRealmInstance.beginTransaction();
        MovieDetails movie = mRealmInstance.where(MovieDetails.class).equalTo(Constants.REALM_MOVIE_ID_QUERY_KEY, movieId).findFirst();
        if (movie != null) {
            movie.setFavorite(false);
        }
        mRealmInstance.commitTransaction();
    }


    @Override
    public void saveMovieDetailsForFirstPage(List<MovieDetails> movieDetailsList) {
        mRealmInstance.beginTransaction();
        mRealmInstance.copyToRealmOrUpdate(createMovieDetailsToSave(movieDetailsList));
        mRealmInstance.commitTransaction();
    }

    @Override
    public void updateMovieDetails(MovieDetails movieDetails) {
        if (movieDetails != null) {
            mRealmInstance.beginTransaction();
            mRealmInstance.copyToRealmOrUpdate(movieDetails);
            mRealmInstance.commitTransaction();
        }
    }

    protected List<MovieDetails> createMovieDetailsToSave(List<MovieDetails> moviesFromNetwork) {
        for (MovieDetails current : moviesFromNetwork) {
            MovieDetails saved = mRealmInstance.where(MovieDetails.class).equalTo(Constants.REALM_MOVIE_ID_QUERY_KEY, current.getMovieId()).findFirst();
            if (saved != null) {
                current.setFavorite(saved.isFavorite());
            }
        }
        return moviesFromNetwork;
    }
}
