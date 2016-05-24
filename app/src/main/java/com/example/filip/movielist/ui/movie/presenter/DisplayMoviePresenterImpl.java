package com.example.filip.movielist.ui.movie.presenter;

import com.example.filip.movielist.api.ResponseListener;
import com.example.filip.movielist.api.database.RealmDatabaseHelper;
import com.example.filip.movielist.api.network.NetworkingHelper;
import com.example.filip.movielist.pojo.MovieGenre;
import com.example.filip.movielist.pojo.MovieWrapper;
import com.example.filip.movielist.ui.movie.view.DisplayMovieView;
import com.example.filip.movielist.utils.StringUtils;

import io.realm.RealmList;

/**
 * Created by Filip on 01/05/2016.
 */
public class DisplayMoviePresenterImpl implements DisplayMoviePresenter {
    private final DisplayMovieView displayMovieView;
    private final NetworkingHelper networkingHelper;
    private final RealmDatabaseHelper databaseHelper;
    private MovieWrapper currentMovie;
    private long movieId;
    private boolean isFavorite;

    public DisplayMoviePresenterImpl(DisplayMovieView displayMovieView, NetworkingHelper networkingHelper, RealmDatabaseHelper databaseHelper) {
        this.displayMovieView = displayMovieView;
        this.networkingHelper = networkingHelper;
        this.databaseHelper = databaseHelper;
    }

    @Override
    public void setMovieId(long movieId) {
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
        networkingHelper.getMovieById(movieId, new ResponseListener<MovieWrapper>() {
            @Override
            public void onSuccess(MovieWrapper callback) {
                setCurrentMovie(callback); //sets it so that the user can favorite it later
                setMovieDetailsIntoUI(currentMovie);
            }

            @Override
            public void onFailure(Throwable throwable) {
                StringUtils.logError(throwable);
            }
        });
    }

    @Override
    public void requestMovieFromDatabase() {
        MovieWrapper movieToLoad = databaseHelper.getMovieFromFavorites(movieId);
        if (movieToLoad != null) {
            setMovieDetailsIntoUI(movieToLoad);
            displayMovieView.favoriteMovieFloatingButton();
            setCurrentMovie(movieToLoad);
            setMovieIsFavorite(true);
        } else displayMovieView.onFailedToLoadMovieShowToastError();
    }

    @Override
    public void handleFavoriteMovieFloatingButtonClick() {
        if (isFavorite) {// if it is favorite already
            displayMovieView.unFavoriteMovieFloatingButton();
            setMovieIsFavorite(false);
        } else {
            displayMovieView.favoriteMovieFloatingButton();
            setMovieIsFavorite(true);
        }
    }

    private void addMovieToFavorites() {
        if (currentMovie != null) {
            databaseHelper.saveMovieToFavorite(currentMovie);
        }
    }

    private void removeMovieFromFavorites() {
        if (currentMovie != null) {
            databaseHelper.removeMovieFromFavorites(movieId);
        }
    }

    @Override
    public void loadMovieIntoUI() {
        if (databaseHelper.checkIfUserAddedMovieToFavorite(movieId)) {
            displayMovieView.loadMovieFromDatabase();
        } else {
            displayMovieView.loadMovieFromNetwork();
        }
    }

    private void setCurrentMovie(MovieWrapper currentMovie) {
        this.currentMovie = currentMovie;
    }

    private void setMovieDetailsIntoUI(MovieWrapper movieToLoad) {
        displayMovieView.setMoviePoster(movieToLoad.getPosterURL());
        displayMovieView.setMovieTitle(movieToLoad.getMovieTitle());
        displayMovieView.setMovieDescription(movieToLoad.getMovieDescription());
        displayMovieView.setMovieGenres(StringUtils.getGenresString(movieToLoad.getGenreList()));
        displayMovieView.setMovieReleaseDate(movieToLoad.getReleaseDate());
        displayMovieView.setMovieRevenue(String.valueOf(movieToLoad.getMovieRevenue()));
        displayMovieView.setMovieRuntime(String.valueOf(movieToLoad.getMovieRuntime()));
    }
}
