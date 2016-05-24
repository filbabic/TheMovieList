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
import android.widget.Toast;

import com.example.filip.movielist.constants.Constants;
import com.example.filip.movielist.App;
import com.example.filip.movielist.ui.database.view.DatabaseActivity;
import com.example.filip.movielist.ui.favorite.view.FavoriteMoviesActivity;
import com.example.filip.movielist.ui.main.adapter.MoviePagerAdapter;
import com.example.filip.movielist.R;
import com.example.filip.movielist.ui.main.presenter.MainActivityPresenter;
import com.example.filip.movielist.ui.main.presenter.MainActivityPresenterImpl;
import com.example.filip.movielist.ui.movie.view.MovieListFragment;
import com.example.filip.movielist.ui.search.MovieSearchActivity;
import com.example.filip.movielist.ui.username.view.ChangeUsernameActivity;
import com.example.filip.movielist.utils.ConnectionUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainActivityView, NavigationView.OnNavigationItemSelectedListener {

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

    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        initViewPager();
        initFAB();
        initNavigationDrawer();
        initPresenter();
        presenter.handleInternetConnectionStatus(ConnectionUtils.checkIfInternetConnectionIsAvailable(App.get()));
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

    private void initPresenter() {
        presenter = new MainActivityPresenterImpl(this);
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
        mNavigationViewDrawer.setNavigationItemSelectedListener(this);
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

    @Override
    public void startDatabaseActivity() {
        startActivity(new Intent(this, DatabaseActivity.class));
    }

    @Override
    public void startChangeUsernameActivity() {
        startActivity(new Intent(this, ChangeUsernameActivity.class));
    }

    @Override
    public void startFavoriteMoviesActivity() {
        startActivity(new Intent(this, FavoriteMoviesActivity.class));
    }

    @Override
    public void showNoInternetConnectionToast() {
        Toast.makeText(App.get(), R.string.no_internet_connection_toast_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        presenter.handleNavigationDrawerItemClick(item.getItemId());
        mDrawerLayout.closeDrawers();
        return false;
    }
}