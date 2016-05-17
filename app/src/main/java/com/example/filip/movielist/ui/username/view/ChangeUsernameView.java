package com.example.filip.movielist.ui.username.view;

/**
 * Created by Filip on 28/04/2016.
 */
public interface ChangeUsernameView {
    void saveChosenUsernameToSharedPreferences(String usernameToSave);

    void onSuccess();

    void onFailure();
}
