package com.example.filip.movielist.utils;

import android.os.Bundle;

import com.example.filip.movielist.constants.Constants;
import com.example.filip.movielist.pojo.ListMovieItem;
import com.example.filip.movielist.pojo.ListMovies;
import com.example.filip.movielist.pojo.MovieWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 25/04/2016.
 */
public class DataUtils {

    public static Bundle createBundle(String movieType) {
        Bundle bundleToAdd = new Bundle();
        bundleToAdd.putString(Constants.MOVIE_TYPE_KEY, movieType);
        return bundleToAdd;
    }

    public static List<ListMovieItem> addMovieTypeKey(ListMovies moviesToAddTo, String movieType) {
        List<ListMovieItem> movieItems = moviesToAddTo.getResults();
        for (ListMovieItem current : movieItems) {
            current.setMovieType(movieType);
        }
        return movieItems;
    }

    public static List<ListMovieItem> getFavoriteMovieListItems(List<MovieWrapper> movieWrappers) {
        List<ListMovieItem> listMovieItems = new ArrayList<>();
        for (MovieWrapper x : movieWrappers) {
            ListMovieItem item = new ListMovieItem(x.getPosterURL(), x.getMovieId(), x.getMovieTitle(), x.getMovieDescription(), x.getReleaseDate());
            listMovieItems.add(item);
        }
        return listMovieItems;
    }
}
