package com.example.filip.movielist.ui.main.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.filip.movielist.constants.Constants;
import com.example.filip.movielist.App;
import com.example.filip.movielist.ui.database.view.DatabaseActivity;
import com.example.filip.movielist.ui.favorite.view.FavoriteMoviesActivity;
import com.example.filip.movielist.ui.main.adapter.MoviePagerAdapter;
import com.example.filip.movielist.R;
import com.example.filip.movielist.ui.movie.view.MovieListFragment;
import com.example.filip.movielist.ui.search.MovieSearchActivity;
import com.example.filip.movielist.ui.username.view.ChangeUsernameActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.main_activity_drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.main_activity_navigation_view)
    NavigationView mNavigationViewDrawer;

    @Bind(R.id.main_activity_view_pager)
    ViewPager mMovieListViewPager;

    @Bind(R.id.main_activity_floating_action_button)
    FloatingActionButton mSearchFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        initViewPager();
        initFAB();
        initNavigationDrawer();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setNavigationDrawerUsername();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            openNavigationDrawer();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openNavigationDrawer() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    private void initToolbar() {
        mToolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initNavigationDrawer() {
        mNavigationViewDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                handleNavigationDrawerItemClick(item.getItemId());
                mDrawerLayout.closeDrawers();
                return false;
            }
        });
    }

    private void initViewPager() {
        MoviePagerAdapter mMoviePagerAdapter = new MoviePagerAdapter(getSupportFragmentManager());

        mMoviePagerAdapter.addItemToAdapter(MovieListFragment.newInstance(Constants.MOVIE_TYPE_TOP_RATED), Constants.MOVIE_TYPE_TOP_RATED);
        mMoviePagerAdapter.addItemToAdapter(MovieListFragment.newInstance(Constants.MOVIE_TYPE_UPCOMING), Constants.MOVIE_TYPE_UPCOMING);
        mMoviePagerAdapter.addItemToAdapter(MovieListFragment.newInstance(Constants.MOVIE_TYPE_POPULAR), Constants.MOVIE_TYPE_POPULAR);

        mMovieListViewPager.setOffscreenPageLimit(3);
        mMovieListViewPager.setAdapter(mMoviePagerAdapter);
    }

    private void initFAB() {
        mSearchFloatingActionButton.setOnClickListener(this);
    }

    private void handleNavigationDrawerItemClick(int itemId) {
        switch (itemId) {
            case R.id.action_database: {
                startActivity(new Intent(this, DatabaseActivity.class));
                break;
            }
            case R.id.action_username: {
                startActivity(new Intent(this, ChangeUsernameActivity.class));
                break;
            }
            case R.id.action_favorite: {
                startActivity(new Intent(this, FavoriteMoviesActivity.class));
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == mSearchFloatingActionButton) {
            startActivity(new Intent(this, MovieSearchActivity.class));
            overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
        }
    }

    private void setNavigationDrawerUsername() {
        SharedPreferences preferences = App.get().getPreferences();
        TextView userTextView = (TextView) mNavigationViewDrawer.getHeaderView(0).findViewById(R.id.main_activity_nav_bar_username_text_view);
        userTextView.setText(preferences.getString(getString(R.string.username_for_application_user), getString(R.string.guest_user)));
    }
}