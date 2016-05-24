package com.example.filip.movielist.ui.main.presenter;

/**
 * Created by flip6 on 20.5.2016..
 */
public interface MainActivityPresenter {
    void handleNavigationDrawerItemClick(int itemClickedID);

    void handleInternetConnectionStatus(boolean hasInternet);
}
