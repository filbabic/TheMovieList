package com.example.filip.movielist.ui.movie.view;

/**
 * Created by Filip on 01/05/2016.
 */
public interface DisplayMovieView {
    void setMoviePoster(String posterPath);

    void setMovieTitle(String movieTitle);

    void setMovieDescription(String movieDescription);

    void setMovieGenres(String movieGenres);

    void setMovieReleaseDate(String movieReleaseDate);

    void setMovieRuntime(String movieRuntime);

    void setMovieRevenue(String movieRevenue);

    void favoriteMovieFloatingButton();

    void unFavoriteMovieFloatingButton();

    void onFailedToLoadMovieShowToastError();

    void loadMovieFromDatabase();

    void loadMovieFromNetwork();

    void showFloatingActionButton();

    void hideFloatingActionButton();
}
