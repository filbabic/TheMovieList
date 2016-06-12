package com.example.filip.movielist.pojo;

import java.util.List;

/**
 * Created by Filip on 23/04/2016.
 */
public class ListOfMovies {
    private final List<MovieListModel> results;

    public ListOfMovies(List<MovieListModel> results) {
        this.results = results;
    }

    public List<MovieListModel> getResults() {
        return results;
    }
}
