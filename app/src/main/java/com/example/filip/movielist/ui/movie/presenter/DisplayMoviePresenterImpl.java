package com.example.filip.movielist.ui.movie.presenter;

import com.example.filip.movielist.api.ResponseListener;
import com.example.filip.movielist.api.database.RealmDatabaseHelper;
import com.example.filip.movielist.api.network.NetworkingHelper;
import com.example.filip.movielist.pojo.MovieDetails;
import com.example.filip.movielist.ui.movie.view.DisplayMovieView;
import com.example.filip.movielist.utils.StringUtils;

/**
 * Created by Filip on 01/05/2016.
 */
public class DisplayMoviePresenterImpl implements DisplayMoviePresenter {
    private final DisplayMovieView displayMovieView;
    private final NetworkingHelper networkingHelper;
    private final RealmDatabaseHelper databaseHelper;
    private MovieDetails currentMovie;
    private int movieId;
    private boolean isFavorite;

    public DisplayMoviePresenterImpl(DisplayMovieView displayMovieView, NetworkingHelper networkingHelper, RealmDatabaseHelper databaseHelper) {
        this.displayMovieView = displayMovieView;
        this.networkingHelper = networkingHelper;
        this.databaseHelper = databaseHelper;
    }

    @Override
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public void setMovieIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    @Override
    public void handleNestedScrolling(int scrollY, boolean isButtonShown) {
        if (scrollY > 0 && isButtonShown) {
            displayMovieView.hideFloatingActionButton();
        } else {
            displayMovieView.showFloatingActionButton();
        }
    }

    @Override
    public void handleMovieIsSetAsFavoriteWhenLeavingActivity() {
        if (isFavorite) {
            addMovieToFavorites();
        } else {
            removeMovieFromFavorites();
        }
    }

    @Override
    public void requestMovieFromNetwork() {
        networkingHelper.getMovieByID(movieId, new ResponseListener<MovieDetails>() {
            @Override
            public void onSuccess(MovieDetails callback) {
                setCurrentMovie(callback); //sets it so that the user can favorite it later
                setMovieDetailsIntoUI(callback);
                callback.setFavorite(isFavorite);
                databaseHelper.updateMovieDetails(callback);
            }

            @Override
            public void onFailure(Throwable throwable) {
                StringUtils.logError(throwable);
            }
        });
    }

    @Override
    public void requestMovieFromRealm() {
        MovieDetails movieToLoad = databaseHelper.getMovieByIdIfCached(movieId);
        if (movieToLoad != null) {
            setMovieDetailsIntoUI(movieToLoad);
            if (movieToLoad.isFavorite()) {
                displayMovieView.setMovieFloatingActionButtonToFavorite();
                setMovieIsFavorite(true);
            }
            setCurrentMovie(movieToLoad);
        } else displayMovieView.onFailedToLoadMovieShowToastError();
        displayMovieView.getMovieFromNetwork(); //also requests the full movie, to load complete data
    }

    @Override
    public void handleFavoriteMovieFloatingButtonClick() {
        if (isFavorite) {// if it is favorite already
            displayMovieView.setMovieFloatingActionButtonToNotFavorite();
            setMovieIsFavorite(false);
        } else {
            displayMovieView.setMovieFloatingActionButtonToFavorite();
            setMovieIsFavorite(true);
        }
    }

    private void addMovieToFavorites() {
        databaseHelper.saveMovieToFavorite(currentMovie);
    }

    private void removeMovieFromFavorites() {
        databaseHelper.removeMovieFromFavorites(movieId);
    }

    @Override
    public void loadMovieIntoUI() {
        if (databaseHelper.checkIfMovieIsCached(movieId)) {
            displayMovieView.getCachedMovieFromDatabase();
        } else {
            displayMovieView.getMovieFromNetwork();
        }
    }

    private void setCurrentMovie(MovieDetails currentMovie) {
        this.currentMovie = currentMovie;
    }

    private void setMovieDetailsIntoUI(MovieDetails movieToLoad) {
        displayMovieView.setMoviePoster(movieToLoad.getPosterURL());
        displayMovieView.setMovieTitle(movieToLoad.getMovieTitle());
        displayMovieView.setMovieDescription(movieToLoad.getMovieDescription());
        displayMovieView.setMovieGenres(StringUtils.getGenresString(movieToLoad.getGenreList()));
        displayMovieView.setMovieReleaseDate(movieToLoad.getReleaseDate());
        displayMovieView.setMovieRevenue(String.valueOf(movieToLoad.getMovieRevenue()));
        displayMovieView.setMovieRuntime(String.valueOf(movieToLoad.getMovieRuntime()));
        displayMovieView.setMovieReleaseStatus(movieToLoad.getMovieStatus());
        displayMovieView.setMovieVoteAverage(String.valueOf(movieToLoad.getMovieGrade()));
    }
}
