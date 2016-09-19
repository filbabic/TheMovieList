package com.example.filip.movielist.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Filip on 23/04/2016.
 */
public class MovieDetailsResponse extends RealmObject {

    @SerializedName("genres")
    private RealmList<MovieGenreModel> genreList;

    @PrimaryKey
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

    private String movieType;
    private boolean isFavorite;

    public MovieDetailsResponse(RealmList<MovieGenreModel> genreList, boolean isFavorite, String movieDescription, float movieGrade, int movieId, long movieRevenue, int movieRuntime, String movieStatus, String movieTitle, String movieType, String posterURL, String releaseDate) {
        this.genreList = genreList;
        this.isFavorite = isFavorite;
        this.movieDescription = movieDescription;
        this.movieGrade = movieGrade;
        this.movieId = movieId;
        this.movieRevenue = movieRevenue;
        this.movieRuntime = movieRuntime;
        this.movieStatus = movieStatus;
        this.movieTitle = movieTitle;
        this.movieType = movieType;
        this.posterURL = posterURL;
        this.releaseDate = releaseDate;
    }

    public MovieDetailsResponse() {
    }

    public List<MovieGenreModel> getGenreList() {
        return genreList != null ? genreList : new ArrayList<MovieGenreModel>();
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

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
}
