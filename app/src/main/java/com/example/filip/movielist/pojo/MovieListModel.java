package com.example.filip.movielist.pojo;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Filip on 01/05/2016.
 */
public class MovieListModel extends RealmObject {
    @SerializedName("poster_path")
    private  String moviePosterPath;

    @SerializedName("id")
    private int movieID;

    @SerializedName("title")
    private  String movieTitle;

    @SerializedName("overview")
    private  String movieDetails;

    @SerializedName("release_date")
    private  String movieReleaseDate;

    private String movieType;

    public MovieListModel(String moviePosterPath, int movieID, String movieTitle, String movieDetails, String movieReleaseDate) {
        this.moviePosterPath = moviePosterPath;
        this.movieID = movieID;
        this.movieTitle = movieTitle;
        this.movieDetails = movieDetails;
        this.movieReleaseDate = movieReleaseDate;
    }

    public MovieListModel() {
    }

    public String getMoviePosterPath() {
        return moviePosterPath;
    }

    public int getMovieID() {
        return movieID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieDetails() {
        return movieDetails;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }
}
