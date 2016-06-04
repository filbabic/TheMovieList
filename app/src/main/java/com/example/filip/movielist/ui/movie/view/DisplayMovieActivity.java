package com.example.filip.movielist.ui.movie.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.filip.movielist.R;
import com.example.filip.movielist.api.database.RealmDatabaseHelper;
import com.example.filip.movielist.api.network.NetworkingHelper;
import com.example.filip.movielist.constants.Constants;
import com.example.filip.movielist.App;
import com.example.filip.movielist.ui.movie.presenter.DisplayMoviePresenter;
import com.example.filip.movielist.ui.movie.presenter.DisplayMoviePresenterImpl;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Filip on 30/04/2016.
 */
public class DisplayMovieActivity extends AppCompatActivity implements DisplayMovieView, View.OnClickListener, NestedScrollView.OnScrollChangeListener {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.activity_movie_details_movie_poster_image_view)
    ImageView mMoviePosterImageView;

    @Bind(R.id.activity_movie_details_movie_description_text_view)
    TextView mMovieDescriptionTextView;

    @Bind(R.id.activity_movie_details_movie_genres_text_view)
    TextView mMovieGenresTextView;

    @Bind(R.id.activity_movie_details_movie_release_date_text_view)
    TextView mMovieReleaseDateTextView;

    @Bind(R.id.activity_movie_details_movie_runtime_text_view)
    TextView mMovieRuntimeTextView;

    @Bind(R.id.activity_movie_details_movie_revenue_text_view)
    TextView mMovieRevenueTextView;

    @Bind(R.id.activity_movie_details_movie_vote_average_text_view)
    TextView mMovieVoteAverageTextView;

    @Bind(R.id.activity_movie_details_movie_release_status_text_view)
    TextView mMovieReleaseStatusTextView;

    @Bind(R.id.activity_movie_details_favorite_floating_action_button)
    FloatingActionButton mFavoriteMovieFloatingActionButton;

    @Bind(R.id.activity_movie_details_nested_scroll_view)
    NestedScrollView mNestedScrollView;

    private DisplayMoviePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movie_details);
        ButterKnife.bind(this);
        initPresenter();
        initScrollView();
        initFloatingActionButton();
        presenter.loadMovieIntoUI();
    }

    @Override
    public void onBackPressed() {
        presenter.handleMovieIsSetAsFavoriteWhenLeavingActivity();
        supportFinishAfterTransition();
    }

    @Override
    public void setMoviePoster(String posterPath) {
        Glide.with(this).load(Constants.IMAGE_URL + posterPath).into(mMoviePosterImageView);
    }

    @Override
    public void setMovieTitle(String movieTitle) {
        mToolbar.setTitle(movieTitle);
    }

    @Override
    public void setMovieDescription(String movieDescription) {
        mMovieDescriptionTextView.setText(movieDescription);
    }

    @Override
    public void setMovieGenres(String movieGenres) {
        mMovieGenresTextView.setText(movieGenres);
    }

    @Override
    public void setMovieReleaseDate(String movieReleaseDate) {
        mMovieReleaseDateTextView.setText(movieReleaseDate);
    }

    @Override
    public void setMovieRuntime(String movieRuntime) {
        mMovieRuntimeTextView.setText(String.format(Locale.getDefault(), getString(R.string.movie_details_movie_runtime_string), movieRuntime));
    }

    @Override
    public void setMovieRevenue(String movieRevenue) {
        mMovieRevenueTextView.setText(String.format(Locale.getDefault(), getString(R.string.movie_details_movie_revenue_string), movieRevenue));
    }

    @Override
    public void setMovieVoteAverage(String movieVoteAverage) {
        mMovieVoteAverageTextView.setText(String.format(Locale.getDefault(), getString(R.string.movie_details_movie_vote_average_string), movieVoteAverage));
    }

    @Override
    public void setMovieReleaseStatus(String movieReleaseStatus) {
        mMovieReleaseStatusTextView.setText(movieReleaseStatus);
    }

    @Override
    public void setMovieFloatingActionButtonToFavorite() {
        mFavoriteMovieFloatingActionButton.setImageResource(R.drawable.ic_favorite_white_24dp);
    }

    @Override
    public void setMovieFloatingActionButtonToNotFavorite() {
        mFavoriteMovieFloatingActionButton.setImageResource(R.drawable.ic_favorite_border_white_24dp);
    }

    @Override
    public void onFailedToLoadMovieShowToastError() {
        Toast.makeText(App.get(), R.string.movie_load_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCachedMoviesFromDatabase() {
        presenter.requestMoviesFromRealm();
    }

    @Override
    public void getMoviesFromNetwork() {
        presenter.requestMovieFromNetwork();
    }

    @Override
    public void showFloatingActionButton() {
        mFavoriteMovieFloatingActionButton.show();
    }

    @Override
    public void hideFloatingActionButton() {
        mFavoriteMovieFloatingActionButton.hide();
    }

    private void initPresenter() {
        RealmDatabaseHelper databaseHelper = App.get().getRealmDatabaseHelper();
        NetworkingHelper networkingHelper = App.get().getNetworkingHelper();
        presenter = new DisplayMoviePresenterImpl(this, networkingHelper, databaseHelper);
        Intent i = getIntent();
        int movieID = i.getIntExtra(Constants.MOVIE_ID_KEY, 0);
        presenter.setMovieId(movieID);
    }

    private void initFloatingActionButton() {
        mFavoriteMovieFloatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mFavoriteMovieFloatingActionButton) {
            presenter.handleFavoriteMovieFloatingButtonClick();
        }
    }

    private void initScrollView() {
        mNestedScrollView.setOnScrollChangeListener(this);
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        presenter.handleNestedScrolling(scrollY, mFavoriteMovieFloatingActionButton.isShown());
    }
}