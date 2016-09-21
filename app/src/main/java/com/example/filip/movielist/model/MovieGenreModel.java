package com.example.filip.movielist.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Filip on 23/04/2016.
 */
public class MovieGenreModel extends RealmObject {

    @SerializedName("name")
    private String genreName;

    public MovieGenreModel(String genreName) {
        this.genreName = genreName;
    }

    public MovieGenreModel() {
    }

    public String getGenreName() {
        return genreName;
    }
}
