package com.example.filip.movielist.api.network;

import com.example.filip.movielist.api.ResponseListener;
import com.example.filip.movielist.pojo.ListMovieItem;
import com.example.filip.movielist.pojo.MovieWrapper;

import java.util.List;

/**
 * Created by Filip on 23/04/2016.
 */
public interface NetworkingHelper {
    void getListOfMoviesForSelectedCategory(String movieType, int pageToLoad, ResponseListener<List<ListMovieItem>> listener);

    void getMovieById(long movieId, ResponseListener<MovieWrapper> listener);
}
