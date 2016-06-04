package com.example.filip.movielist.ui.main.view;

/**
 * Created by flip6 on 20.5.2016..
 */
public interface MainActivityView {
    void startDatabaseActivity();

    void startChangeUsernameActivity();

    void startFavoriteMoviesActivity();

    void showNoInternetConnectionToast();

    void openNavigationDrawer();

    void closeNavigationDrawer();
}
