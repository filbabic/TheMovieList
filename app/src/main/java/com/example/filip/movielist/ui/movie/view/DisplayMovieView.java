package com.example.filip.movielist.ui.movie.view;

/**
 * Created by Filip on 01/05/2016.
 */
public interface DisplayMovieView {
    void loadMoviePoster(String posterPath);

    void loadMovieTitle(String movieTitle);

    void loadMovieDescription(String movieDescription);

    void loadMovieGenres(String movieGenres);

    void loadMovieReleaseDate(String movieReleaseDate);

    void loadMovieRuntime(String movieRuntime);

    void loadMovieRevenue(String movieRevenue);

    void setFloatingButtonDrawable(boolean isFavorite);

    void onFailure();
}
