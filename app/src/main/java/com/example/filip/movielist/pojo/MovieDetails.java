package com.example.filip.movielist.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Filip on 23/04/2016.
 */
public class MovieDetails extends RealmObject {
    @SerializedName("genres")
    private RealmList<MovieGenreModel> genreList;

    @SerializedName("id")
    private int movieId;

    @SerializedName("title")
    private String movieTitle;

    @SerializedName("overview")
    private String movieDescription;

    @SerializedName("poster_path")
    private String posterURL;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("revenue")
    private long movieRevenue;

    @SerializedName("runtime")
    private int movieRuntime;

    @SerializedName("vote_average")
    private float movieGrade;

    @SerializedName("status")
    private String movieStatus;

    public MovieDetails(RealmList<MovieGenreModel> genreList, int movieId, String movieTitle, String movieDescription, String posterURL, String releaseDate, long movieRevenue, int movieRuntime, float movieGrade, String movieStatus) {
        this.genreList = genreList;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieDescription = movieDescription;
        this.posterURL = posterURL;
        this.releaseDate = releaseDate;
        this.movieRevenue = movieRevenue;
        this.movieRuntime = movieRuntime;
        this.movieGrade = movieGrade;
        this.movieStatus = movieStatus;
    }

    public MovieDetails() {
    }

    public List<MovieGenreModel> getGenreList() {
        return genreList;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public long getMovieRevenue() {
        return movieRevenue;
    }

    public int getMovieRuntime() {
        return movieRuntime;
    }

    public float getMovieGrade() {
        return movieGrade;
    }

    public String getMovieStatus() {
        return movieStatus;
    }
}
