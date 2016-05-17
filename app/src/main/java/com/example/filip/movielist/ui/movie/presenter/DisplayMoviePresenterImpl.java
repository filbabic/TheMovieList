package com.example.filip.movielist.ui.movie.presenter;

import com.example.filip.movielist.api.ResponseListener;
import com.example.filip.movielist.api.database.RealmDatabaseHelper;
import com.example.filip.movielist.api.network.NetworkingHelper;
import com.example.filip.movielist.pojo.MovieWrapper;
import com.example.filip.movielist.ui.movie.view.DisplayMovieView;
import com.example.filip.movielist.utils.StringUtils;

/**
 * Created by Filip on 01/05/2016.
 */
public class DisplayMoviePresenterImpl implements DisplayMoviePresenter {
    private final DisplayMovieView displayMovieView;
    private final NetworkingHelper networkingHelper;
    private final RealmDatabaseHelper databaseHelper;
    private MovieWrapper currentMovie; //

    public DisplayMoviePresenterImpl(DisplayMovieView displayMovieView, NetworkingHelper networkingHelper, RealmDatabaseHelper databaseHelper, MovieWrapper currentMovie) {
        this.displayMovieView = displayMovieView;
        this.networkingHelper = networkingHelper;
        this.databaseHelper = databaseHelper;
        this.currentMovie = currentMovie;
    }

    @Override
    public void requestMovieFromNetwork(long movieId) {
        networkingHelper.getMovieById(movieId, new ResponseListener<MovieWrapper>() {
            @Override
            public void onSuccess(MovieWrapper callback) {
                loadMovieIntoUI(callback);
                setCurrentMovie(callback); //sets it so that the user can favorite it later
            }

            @Override
            public void onFailure(Throwable throwable) {
                StringUtils.logError(throwable);
            }
        });
    }

    @Override
    public void requestMovieFromDatabase(long movieId) {
        MovieWrapper movieToLoad = databaseHelper.getMovieFromFavorites(movieId);
        if (movieToLoad != null) {
            loadMovieIntoUI(movieToLoad);
            displayMovieView.setFloatingButtonDrawable(false);
            setCurrentMovie(movieToLoad);
        } else displayMovieView.onFailure();
    }

    @Override
    public boolean checkIfMovieIsCached(long movieId) {
        return databaseHelper.checkIfUserAddedMovieToFavorite(movieId);
    }

    @Override
    public void addMovieToFavorites() {
        if (currentMovie != null) {
            displayMovieView.setFloatingButtonDrawable(false);
            databaseHelper.saveMovieToFavorite(currentMovie);
        }
    }

    @Override
    public void removeMovieFromFavorites(long movieId) {
        displayMovieView.setFloatingButtonDrawable(true);
        databaseHelper.removeMovieFromFavorites(movieId);
    }

    private void setCurrentMovie(MovieWrapper currentMovie) {
        this.currentMovie = currentMovie;
    }

    private void loadMovieIntoUI(MovieWrapper movieToLoad) {
        displayMovieView.loadMoviePoster(movieToLoad.getPosterURL());
        displayMovieView.loadMovieTitle(movieToLoad.getMovieTitle());
        displayMovieView.loadMovieDescription(movieToLoad.getMovieDescription());
        displayMovieView.loadMovieGenres(StringUtils.getGenresString(movieToLoad.getGenreList()));
        displayMovieView.loadMovieReleaseDate(movieToLoad.getReleaseDate());
        displayMovieView.loadMovieRevenue(String.valueOf(movieToLoad.getMovieRevenue()));
        displayMovieView.loadMovieRuntime(String.valueOf(movieToLoad.getMovieRuntime()));
    }
}
