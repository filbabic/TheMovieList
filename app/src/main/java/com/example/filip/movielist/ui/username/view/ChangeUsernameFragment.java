package com.example.filip.movielist.ui.username.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.filip.movielist.R;
import com.example.filip.movielist.App;
import com.example.filip.movielist.ui.username.presenter.ChangeUsernamePresenter;
import com.example.filip.movielist.ui.username.presenter.ChangeUsernamePresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Filip on 27/04/2016.
 */
public class ChangeUsernameFragment extends Fragment implements View.OnClickListener, ChangeUsernameView {
    @Bind(R.id.fragment_username_change_view_switcher)
    ViewSwitcher mViewSwitcher;

    @Bind(R.id.fragment_username_change_username_text_view)
    TextView mUsernameTextView;

    @Bind(R.id.fragment_username_change_username_edit_text)
    EditText mUsernameEditText;

    @Bind(R.id.fragment_username_change_username_change_button)
    Button mSaveUsernameButton;

    private ChangeUsernamePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_username_change, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initUI();
        initPresenter();
    }

    private void initUI() {
        SharedPreferences preferences = App.get().getPreferences();
        String username = preferences.getString(getString(R.string.username_for_application_user), getString(R.string.guest_user));
        mUsernameTextView.setText(username);
        mUsernameEditText.setText(username);
        mUsernameTextView.setOnClickListener(this);
        mSaveUsernameButton.setOnClickListener(this);
    }

    private void initPresenter() {
        presenter = new ChangeUsernamePresenterImpl(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveUsernameButton) {
            presenter.checkIfDataIsValid(mUsernameEditText.getText().toString());
        }
        if (v == mUsernameTextView) mViewSwitcher.showNext();
    }

    @Override
    public void saveChosenUsernameToSharedPreferences(String usernameToSave) {
        SharedPreferences sharedPreferences = App.get().getPreferences();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.username_for_application_user), usernameToSave);
        editor.apply();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(App.get(), R.string.username_changed_successfully_toast_message, Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }

    @Override
    public void onFailure() {
        mUsernameEditText.setError(getString(R.string.username_change_error_message));
    }
}
