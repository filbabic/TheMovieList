package com.example.filip.movielist.ui.movie.presenter;


/**
 * Created by Filip on 01/05/2016.
 */
public interface DisplayMoviePresenter {
    void requestMovieFromNetwork();

    void requestMovieFromDatabase();

    void handleFavoriteMovieFloatingButtonClick();

    void loadMovieIntoUI();

    void setMovieId(long movieId);

    void setMovieIsFavorite(boolean isFavorite);

    void handleNestedScrolling(int scrollY, boolean isButtonShown);

    void handleMovieIsSetAsFavoriteWhenLeavingActivity();
}
