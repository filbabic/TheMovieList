package com.example.filip.movielist.ui.database.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.filip.movielist.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filip on 03/05/2016.
 */
public class CachedMoviesRecyclerAdapter extends RecyclerView.Adapter<CachedMoviesRecyclerAdapter.ViewHolder> {
    private final List<String> mMovieItems = new ArrayList<>();

    public void setAdapterItems(List<String> mDataSource) {
        mMovieItems.clear();
        mMovieItems.addAll(mDataSource);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mMovieItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View current = LayoutInflater.from(parent.getContext()).inflate(R.layout.cached_movies_recycler_item, parent, false);
        return new ViewHolder(current);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String currentTitle = mMovieItems.get(position);
        holder.loadMovieTitle(currentTitle);
    }

    @Override
    public int getItemCount() {
        return mMovieItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTitleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.cached_movie_list_item_title_text_view);
        }

        public void loadMovieTitle(String movieTitle) {
            mTitleTextView.setText(movieTitle);
        }
    }
}
