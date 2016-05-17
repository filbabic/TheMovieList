package com.example.filip.movielist.pojo;

import java.util.List;

/**
 * Created by Filip on 23/04/2016.
 */
public class ListMovies {
    List<ListMovieItem> results;

    public ListMovies(List<ListMovieItem> results) {
        this.results = results;
    }

    public List<ListMovieItem> getResults() {
        return results;
    }

    public void setResults(List<ListMovieItem> results) {
        this.results = results;
    }

    public ListMovieItem getModelObject(int position) {
        return results.get(position);
    }
}
