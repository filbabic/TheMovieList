package com.example.filip.movielist.api.network;

import com.example.filip.movielist.api.ResponseListener;
import com.example.filip.movielist.pojo.MovieListModel;
import com.example.filip.movielist.pojo.MovieDetails;

import java.util.List;

/**
 * Created by Filip on 23/04/2016.
 */
public interface NetworkingHelper {
    void getListOfMoviesForSelectedCategory(String movieType, int pageToLoad, ResponseListener<List<MovieListModel>> listener);

    void getMovieByID(int movieID, ResponseListener<MovieDetails> listener);
}
