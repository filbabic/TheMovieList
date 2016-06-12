package com.example.filip.movielist.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Filip on 23/04/2016.
 */
public class MovieDetails extends RealmObject {
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

    public MovieDetails(List<MovieGenreModel> genreList, int movieId, String movieTitle, String movieDescription, String posterURL, String releaseDate, long movieRevenue, int movieRuntime, float movieGrade, String movieStatus) {
        this.genreList = (RealmList<MovieGenreModel>) genreList;
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

    public MovieDetails(int movieId, String movieTitle, String movieDescription, String posterURL, String releaseDate, float movieGrade, String movieType) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieDescription = movieDescription;
        this.posterURL = posterURL;
        this.releaseDate = releaseDate;
        this.movieGrade = movieGrade;
        this.movieType = movieType;
        this.movieStatus = "N/A";
    }

    public MovieDetails() {
    }

    public List<MovieGenreModel> getGenreList() {
        if (genreList != null)
            return genreList;
        else return new RealmList<>();
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
