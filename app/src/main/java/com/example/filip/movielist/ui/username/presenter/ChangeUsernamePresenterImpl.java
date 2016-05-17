package com.example.filip.movielist.ui.username.presenter;

import com.example.filip.movielist.ui.username.view.ChangeUsernameView;

/**
 * Created by Filip on 28/04/2016.
 */
public class ChangeUsernamePresenterImpl implements ChangeUsernamePresenter {
    private final ChangeUsernameView changeUsernameView;

    public ChangeUsernamePresenterImpl(ChangeUsernameView changeUsernameView) {
        this.changeUsernameView = changeUsernameView;
    }

    @Override
    public void checkIfDataIsValid(String usernameToCheck) {
        if (!usernameToCheck.isEmpty()) {
            changeUsernameView.saveChosenUsernameToSharedPreferences(usernameToCheck);
            changeUsernameView.onSuccess();
        } else changeUsernameView.onFailure();
    }
}
