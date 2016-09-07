package com.example.filip.movielist.ui.username.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.filip.movielist.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Filip on 29/04/2016.
 */
public class ChangeUsernameActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username_change);
        ButterKnife.bind(this);
        initToolbar();
        if (savedInstanceState == null)
            initFragment();
    }

    private void initFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(R.id.username_change_activity_frame_layout, new ChangeUsernameFragment())
                .commit();
    }

    private void initToolbar() {
        mToolbar.setTitle(R.string.change_username_activity_title);
        setSupportActionBar(mToolbar);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
