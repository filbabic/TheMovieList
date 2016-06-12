package com.example.filip.movielist.ui.movie.presenter;


/**
 * Created by Filip on 01/05/2016.
 */
public interface DisplayMoviePresenter {
    void requestMovieFromNetwork();

    void requestMovieFromRealm();

    void handleFavoriteMovieFloatingButtonClick();

    void loadMovieIntoUI();

    void setMovieId(int movieId);

    void setMovieIsFavorite(boolean isFavorite);

    void handleNestedScrolling(int scrollY, boolean isButtonShown);

    void handleMovieIsSetAsFavoriteWhenLeavingActivity();
}
