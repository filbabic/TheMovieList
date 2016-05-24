package com.example.filip.movielist.ui.movie.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.filip.movielist.R;
import com.example.filip.movielist.api.database.RealmDatabaseHelper;
import com.example.filip.movielist.api.network.NetworkingHelper;
import com.example.filip.movielist.constants.Constants;
import com.example.filip.movielist.pojo.ListMovieItem;
import com.example.filip.movielist.App;
import com.example.filip.movielist.ui.movie.adapter.ItemListener;
import com.example.filip.movielist.ui.movie.adapter.MovieRecyclerAdapter;
import com.example.filip.movielist.ui.movie.presenter.MovieListFragmentPresenter;
import com.example.filip.movielist.ui.movie.presenter.MovieListFragmentPresenterImpl;
import com.example.filip.movielist.utils.ConnectionUtils;
import com.example.filip.movielist.utils.DataUtils;
import com.example.filip.movielist.utils.StringUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Filip on 25/04/2016.
 */
public class MovieListFragment extends Fragment implements MovieView, ItemListener, SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.fragment_list_of_movies_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.fragment_list_of_movies_recycler_view)
    RecyclerView mMoviesRecyclerView;

    private MovieRecyclerAdapter mMovieListAdapter;

    private MovieListFragmentPresenter presenter;

    public static MovieListFragment newInstance(String movieType) {
        MovieListFragment f = new MovieListFragment();
        f.setArguments(DataUtils.createBundle(movieType));
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_of_movies, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initAdapter();
        initUI();
        initPresenter();
    }

    @Override
    public void onStart() {
        super.onStart();
        loadMovies();
    }

    private void loadMovies() {
        if (presenter != null) {
            if (ConnectionUtils.checkIfInternetConnectionIsAvailable(App.get())) {
                presenter.requestMoviesFromNetwork(1);
            } else {
                presenter.requestMoviesFromDatabase();
            }
        }
    }

    @Override
    public void onItemClick(long movieID, View viewToTransit) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), viewToTransit, getString(R.string.image_view_transition));
        Intent i = new Intent(getActivity(), DisplayMovieActivity.class);
        i.putExtra(Constants.MOVIE_ID_KEY, movieID);
        startActivity(i, options.toBundle());
    }

    private void initUI() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMoviesRecyclerView.setHasFixedSize(true);
        mMoviesRecyclerView.setAdapter(mMovieListAdapter);
    }

    private void initAdapter() {
        mMovieListAdapter = new MovieRecyclerAdapter(this);
    }

    private void initPresenter() {
        Bundle movieBundle = this.getArguments();
        if (movieBundle.containsKey(Constants.MOVIE_TYPE_KEY)) {
            String movieTypeKey = movieBundle.getString(Constants.MOVIE_TYPE_KEY);
            if (StringUtils.stringIsEmptyOrNull(movieTypeKey)) {
                Toast.makeText(App.get(), R.string.movie_type_key_is_null_toast_error_message, Toast.LENGTH_SHORT).show();
            } else {
                NetworkingHelper networkingHelper = App.get().getNetworkingHelper();
                RealmDatabaseHelper databaseHelper = App.get().getRealmDatabaseHelper();
                presenter = new MovieListFragmentPresenterImpl(this, networkingHelper, databaseHelper);
                presenter.setMovieTypeKey(movieTypeKey);
            }
        }
    }

    @Override
    public void addItemsToAdapter(List<ListMovieItem> mDataSource) {
        mMovieListAdapter.setItems(mDataSource);
    }

    @Override
    public void onRefresh() {
        loadMovies();
        mSwipeRefreshLayout.setRefreshing(false);
    }
}