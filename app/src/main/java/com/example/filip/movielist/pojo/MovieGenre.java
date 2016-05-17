package com.example.filip.movielist.pojo;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Filip on 23/04/2016.
 */
public class MovieGenre extends RealmObject {
    @SerializedName("name")
    private String genreName;
    @SerializedName("id")
    private int genreId;

    public MovieGenre(String genreName, int genreId) {
        this.genreName = genreName;
        this.genreId = genreId;
    }

    public MovieGenre() {
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }
}
