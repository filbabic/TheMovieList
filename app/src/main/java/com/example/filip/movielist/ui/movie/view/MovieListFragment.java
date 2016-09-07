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

import com.example.filip.movielist.App;
import com.example.filip.movielist.R;
import com.example.filip.movielist.api.database.RealmDatabaseHelper;
import com.example.filip.movielist.api.network.NetworkingHelper;
import com.example.filip.movielist.api.network.NetworkingHelperImpl;
import com.example.filip.movielist.constants.Constants;
import com.example.filip.movielist.pojo.ListMovieItem;
import com.example.filip.movielist.ui.movie.adapter.ItemListener;
import com.example.filip.movielist.ui.movie.adapter.MovieRecyclerAdapter;
import com.example.filip.movielist.ui.movie.presenter.MovieListFragmentPresenter;
import com.example.filip.movielist.ui.movie.presenter.MovieListFragmentPresenterImpl;
import com.example.filip.movielist.utils.ConnectionUtils;
import com.example.filip.movielist.utils.DataUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Filip on 25/04/2016.
 */
public class MovieListFragment extends Fragment implements MovieView, ItemListener {

    @BindView(R.id.fragment_list_of_movies_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.fragment_list_of_movies_recycler_view)
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
        loadMovies();
    }

    private void loadMovies() {
        initPresenter();
        if (ConnectionUtils.checkIfInternetConnectionIsAvailable(App.get())) {
            requestMoviesFromNetworkService();
        } else requestMoviesFromDatabase();
    }

    @Override
    public void onItemClick(long movieID, View viewToTransit) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), viewToTransit, getString(R.string.image_view_transition));
        Intent i = new Intent(getActivity(), DisplayMovieActivity.class);
        i.putExtra(Constants.MOVIE_ID_KEY, movieID);
        startActivity(i, options.toBundle());
    }

    private void initUI() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadMovies();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        mMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMoviesRecyclerView.setHasFixedSize(true);
        mMoviesRecyclerView.setAdapter(mMovieListAdapter);
    }

    private void initAdapter() {
        mMovieListAdapter = new MovieRecyclerAdapter(this);
    }

    private void initPresenter() {
        Bundle movieBundle = this.getArguments();
        String movieTypeKey = movieBundle.getString(Constants.MOVIE_TYPE_KEY);
        NetworkingHelper networkingHelper = new NetworkingHelperImpl(App.get().getMovieService());
        RealmDatabaseHelper databaseHelper = App.get().getRealmDatabaseHelper();
        presenter = new MovieListFragmentPresenterImpl(this, networkingHelper, databaseHelper, movieTypeKey);
    }

    @Override
    public void loadAdapterWithItems(List<ListMovieItem> mDataSource) {
        mMovieListAdapter.setItems(mDataSource);
        cacheLoadedMoviesIntoDatabase(mDataSource);
    }

    @Override
    public void loadCachedMovies(List<ListMovieItem> mDataSource) {
        mMovieListAdapter.setItems(mDataSource);
    }

    @Override
    public void onFailure() {
        Toast.makeText(App.get(), R.string.database_movie_error, Toast.LENGTH_SHORT).show();
    }

    private void requestMoviesFromNetworkService() { //for now its always 1st page // TODO: 01/05/2016 add constant item loading on last item reached
        presenter.requestMoviesFromNetwork(1);
    }

    private void requestMoviesFromDatabase() {
        presenter.loadMoviesFromDatabase();
    }

    private void cacheLoadedMoviesIntoDatabase(List<ListMovieItem> mDataSource) {
        presenter.storeMoviesInDatabase(mDataSource);
    }
}