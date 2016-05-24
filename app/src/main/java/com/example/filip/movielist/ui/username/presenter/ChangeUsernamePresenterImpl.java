package com.example.filip.movielist.ui.username.presenter;

import com.example.filip.movielist.ui.username.view.ChangeUsernameView;
import com.example.filip.movielist.utils.StringUtils;

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
        if (!StringUtils.stringIsEmptyOrNull(usernameToCheck)) {
            changeUsernameView.saveChosenUsernameToSharedPreferences(usernameToCheck);
            changeUsernameView.showSuccessfullyChangedUsernameToast();
        } else changeUsernameView.showErrorMessageOnFailedToChangeUsername();
    }
}
