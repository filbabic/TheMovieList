package com.example.filip.movielist.ui.database.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.filip.movielist.R;
import com.example.filip.movielist.api.database.RealmDatabaseHelper;
import com.example.filip.movielist.App;
import com.example.filip.movielist.ui.database.adapter.CachedMoviesRecyclerAdapter;
import com.example.filip.movielist.ui.database.presenter.DatabaseActivityPresenter;
import com.example.filip.movielist.ui.database.presenter.DatabaseActivityPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Filip on 03/05/2016.
 */
public class DatabaseActivity extends AppCompatActivity implements DatabaseActivityView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.activity_database_cached_movies_recycler_view)
    RecyclerView mRecyclerView;

    private CachedMoviesRecyclerAdapter mAdapter;

    private DatabaseActivityPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        ButterKnife.bind(this);
        initToolbar();
        initAdapter();
        initRecyclerView();
        initPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.requestMoviesFromRealm();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_database, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        handleMenuItemClick(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void fillAdapterWithItems(List<String> mCachedMovieTitles) {
        mAdapter.setAdapterItems(mCachedMovieTitles);
    }

    @Override
    public void onFailure() {
        Toast.makeText(App.get(), R.string.database_movie_error, Toast.LENGTH_SHORT).show();
    }

    private void initToolbar() {
        mToolbar.setTitle(R.string.database_activity_title);
        setSupportActionBar(mToolbar);
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initAdapter() {
        mAdapter = new CachedMoviesRecyclerAdapter();
    }

    private void initPresenter() {
        RealmDatabaseHelper databaseHelper = App.get().getRealmDatabaseHelper();
        presenter = new DatabaseActivityPresenterImpl(databaseHelper, this);
    }

    private void handleMenuItemClick(int itemID) {
        if (itemID == R.id.activity_database_menu_delete_cached_movies_action) {
            showDeleteDialog();
        }
    }

    private void showDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.activity_database_delete_cache_dialog_message));
        builder.setPositiveButton(getString(R.string.dialog_positive_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.deleteMoviesFromRealm();
                presenter.requestMoviesFromRealm();
            }
        });
        builder.setNegativeButton(getString(R.string.dialog_negative_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}