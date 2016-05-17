package com.example.filip.movielist.api;

/**
 * Created by Filip on 23/04/2016.
 */
public interface ResponseListener<T> {
    void onSuccess(T callback);

    void onFailure(Throwable throwable);
}
