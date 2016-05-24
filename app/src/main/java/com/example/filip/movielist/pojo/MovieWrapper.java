package com.example.filip.movielist.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Filip on 23/04/2016.
 */
public class MovieWrapper extends RealmObject {
    @SerializedName("genres")
    private RealmList<MovieGenre> genreList;

    @SerializedName("id")
    private long movieId;

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

    public MovieWrapper(RealmList<MovieGenre> genreList, long movieId, String movieTitle, String movieDescription, String posterURL, String releaseDate, long movieRevenue, int movieRuntime) {
        this.genreList = genreList;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.movieDescription = movieDescription;
        this.posterURL = posterURL;
        this.releaseDate = releaseDate;
        this.movieRevenue = movieRevenue;
        this.movieRuntime = movieRuntime;
    }

    public MovieWrapper() {
    }

    public List<MovieGenre> getGenreList() {
        return genreList;
    }

    public void setGenreList(RealmList<MovieGenre> genreList) {
        this.genreList = genreList;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public long getMovieRevenue() {
        return movieRevenue;
    }

    public void setMovieRevenue(long movieRevenue) {
        this.movieRevenue = movieRevenue;
    }

    public int getMovieRuntime() {
        return movieRuntime;
    }

    public void setMovieRuntime(int movieRuntime) {
        this.movieRuntime = movieRuntime;
    }
}
