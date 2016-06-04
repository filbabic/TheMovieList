package com.example.filip.movielist.ui.main.presenter;

import com.example.filip.movielist.App;
import com.example.filip.movielist.R;
import com.example.filip.movielist.ui.main.view.MainActivityView;
import com.example.filip.movielist.utils.ConnectionUtils;

/**
 * Created by flip6 on 20.5.2016..
 */
public class MainActivityPresenterImpl implements MainActivityPresenter {
    private final MainActivityView mainActivityView;

    public MainActivityPresenterImpl(MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
    }

    @Override
    public void handleNavigationDrawerItemClick(int itemClickedID) {
        switch (itemClickedID) {
            case R.id.action_favorite: {
                mainActivityView.startFavoriteMoviesActivity();
                break;
            }
            case R.id.action_database: {
                mainActivityView.startDatabaseActivity();
                break;
            }
            case R.id.action_username: {
                mainActivityView.startChangeUsernameActivity();
                break;
            }
            default: mainActivityView.closeNavigationDrawer();
        }
    }

    @Override
    public void handleInternetConnectionStatus() {
        if (!ConnectionUtils.checkIfInternetConnectionIsAvailable(App.get())) {
            mainActivityView.showNoInternetConnectionToast();
        }
    }

    @Override
    public void handleUserClickedHomeButton() {
        mainActivityView.openNavigationDrawer();
    }
}
