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
import com.example.filip.movielist.api.network.NetworkingHelperImpl;
import com.example.filip.movielist.constants.Constants;
import com.example.filip.movielist.App;
import com.example.filip.movielist.ui.movie.presenter.DisplayMoviePresenter;
import com.example.filip.movielist.ui.movie.presenter.DisplayMoviePresenterImpl;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Filip on 30/04/2016.
 */
public class DisplayMovieActivity extends AppCompatActivity implements DisplayMovieView, View.OnClickListener {
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadMovieIntoUI();
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }

    @Override
    public void loadMoviePoster(String posterPath) {
        Glide.with(this).load(Constants.IMAGE_URL + posterPath).into(mMoviePosterImageView);
    }

    @Override
    public void loadMovieTitle(String movieTitle) {
        mToolbar.setTitle(movieTitle);
        setSupportActionBar(mToolbar);
    }

    @Override
    public void loadMovieDescription(String movieDescription) {
        mMovieDescriptionTextView.setText(movieDescription);
    }

    @Override
    public void loadMovieGenres(String movieGenres) {
        mMovieGenresTextView.setText(movieGenres);
    }

    @Override
    public void loadMovieReleaseDate(String movieReleaseDate) {
        mMovieReleaseDateTextView.setText(movieReleaseDate);
    }

    @Override
    public void loadMovieRuntime(String movieRuntime) {
        mMovieRuntimeTextView.setText(getString(R.string.movie_details_movie_runtime_string, movieRuntime));
    }

    @Override
    public void loadMovieRevenue(String movieRevenue) {
        mMovieRevenueTextView.setText(getString(R.string.movie_details_movie_revenue_string, movieRevenue));
    }

    @Override
    public void setFloatingButtonDrawable(boolean isFavorite) {
        if (isFavorite)
            mFavoriteMovieFloatingActionButton.setImageResource(R.drawable.ic_favorite_border_white_24dp);
        else
            mFavoriteMovieFloatingActionButton.setImageResource(R.drawable.ic_favorite_white_24dp);
    }

    @Override
    public void onFailure() {
        Toast.makeText(App.get(), R.string.movie_load_error, Toast.LENGTH_SHORT).show();
    }

    private void initPresenter() {
        RealmDatabaseHelper databaseHelper = App.get().getRealmDatabaseHelper();
        NetworkingHelper networkingHelper = new NetworkingHelperImpl(App.get().getMovieService());
        presenter = new DisplayMoviePresenterImpl(this, networkingHelper, databaseHelper, null);
    }

    private void initFloatingActionButton() {
        mFavoriteMovieFloatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mFavoriteMovieFloatingActionButton) {
            handleFloatingActionButtonClick();
        }
    }

    private void loadMovieIntoUI() {
        Intent i = getIntent();
        long movieId = i.getLongExtra(Constants.MOVIE_ID_KEY, -1);
        if (presenter.checkIfMovieIsCached(movieId)) {
            presenter.requestMovieFromDatabase(movieId);
        } else {
            presenter.requestMovieFromNetwork(movieId);
        }
    }

    private void handleFloatingActionButtonClick() {
        Intent i = getIntent();
        long movieId = i.getLongExtra(Constants.MOVIE_ID_KEY, -1);
        boolean isFavorite = presenter.checkIfMovieIsCached(movieId);
        if (isFavorite) {
            presenter.removeMovieFromFavorites(movieId);
        } else {
            presenter.addMovieToFavorites();
        }
    }

    private void initScrollView() {
        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > 0 && mFavoriteMovieFloatingActionButton.isShown())
                    mFavoriteMovieFloatingActionButton.hide();
                else mFavoriteMovieFloatingActionButton.show();
            }
        });
    }
}