package com.example.filip.movielist.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Filip on 01/05/2016.
 */
public class MovieListModel extends RealmObject {

    @SerializedName("poster_path")
    private String moviePosterPath;

    @PrimaryKey
    @SerializedName("id")
    private int movieID;

    @SerializedName("title")
    private String movieTitle;

    @SerializedName("overview")
    private String movieDetails;

    @SerializedName("release_date")
    private String movieReleaseDate;
    @SerializedName("vote_average")
    private float movieGradeAverage;

    private String movieType;

    public MovieListModel(String moviePosterPath, int movieID, String movieTitle, String movieDetails, String movieReleaseDate, float movieGradeAverage) {
        this.moviePosterPath = moviePosterPath;
        this.movieID = movieID;
        this.movieTitle = movieTitle;
        this.movieDetails = movieDetails;
        this.movieReleaseDate = movieReleaseDate;
        this.movieGradeAverage = movieGradeAverage;
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

    public float getMovieGradeAverage() {
        return movieGradeAverage;
    }

    public String getMovieType() {
        return movieType;
    }
}
