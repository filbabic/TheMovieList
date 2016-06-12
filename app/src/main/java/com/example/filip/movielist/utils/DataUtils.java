package com.example.filip.movielist.utils;

import android.os.Bundle;

import com.example.filip.movielist.constants.Constants;
import com.example.filip.movielist.pojo.MovieListModel;
import com.example.filip.movielist.pojo.ListOfMovies;
import com.example.filip.movielist.pojo.MovieDetails;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

/**
 * Created by Filip on 25/04/2016.
 */
public class DataUtils {

    public static Bundle createBundle(String movieType) {
        Bundle bundleToAdd = new Bundle();
        bundleToAdd.putString(Constants.MOVIE_TYPE_KEY, movieType);
        return bundleToAdd;
    }

    public static List<MovieListModel> addMovieTypeKey(ListOfMovies moviesToAddTo, String movieType) {
        List<MovieListModel> movieItems = moviesToAddTo.getResults();
        for (MovieListModel current : movieItems) {
            current.setMovieType(movieType);
        }
        return movieItems;
    }

    public static List<MovieListModel> createFavoriteMoviesListItemsFromCachedMovieDetails(List<MovieDetails> movieWrappers) {
        List<MovieListModel> movieListModels = new ArrayList<>();
        for (MovieDetails x : movieWrappers) {
            MovieListModel item = new MovieListModel(x.getPosterURL(), x.getMovieId(), x.getMovieTitle(), x.getMovieDescription(), x.getReleaseDate(), x.getMovieGrade());
            movieListModels.add(item);
        }
        return movieListModels;
    }

    public static List<MovieDetails> createListOfDetailsMoviesFromMovieListModels(List<MovieListModel> movieListModels, String movieType) {
        List<MovieDetails> movieDetailsList = new ArrayList<>();
        for (MovieListModel x : movieListModels) {
            movieDetailsList.add(new MovieDetails(x.getMovieID(), x.getMovieTitle(), x.getMovieDetails(), x.getMoviePosterPath(), x.getMovieReleaseDate(), x.getMovieGradeAverage(), movieType));
        }
        return movieDetailsList;
    }

    public static List<MovieDetails> createMovieDetailsRealmObjectCopies(RealmResults<MovieDetails> movieDetails) {
        List<MovieDetails> moviesToCreate = new ArrayList<>();
        for (MovieDetails current : movieDetails) {
            moviesToCreate.add(new MovieDetails(current.getMovieId(), current.getMovieTitle(), current.getMovieDescription(), current.getPosterURL(), current.getReleaseDate(), current.getMovieGrade(), null));
        }
        return moviesToCreate;
    }

    public static List<MovieListModel> createMovieListModelCopiesFromRealmObjects(RealmResults<MovieListModel> movieListModels) {
        List<MovieListModel> listModels = new ArrayList<>();
        for (MovieListModel current : movieListModels) {
            listModels.add(new MovieListModel(current.getMoviePosterPath(), current.getMovieID(), current.getMovieTitle(), current.getMovieDetails(), current.getMovieReleaseDate(), current.getMovieGradeAverage()));
        }
        return listModels;
    }
}
