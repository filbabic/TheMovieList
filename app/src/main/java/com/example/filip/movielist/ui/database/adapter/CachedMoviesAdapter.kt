package com.example.filip.movielist.ui.database.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.filip.movielist.R
import com.example.filip.movielist.common.extensions.fill
import com.example.filip.movielist.ui.database.holder.CachedMovieHolder
import java.util.*

/**
 * Created by Filip Babic @cobe
 */
class CachedMoviesAdapter : RecyclerView.Adapter<CachedMovieHolder>() {

    private val mItems: MutableList<String> = ArrayList()

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: CachedMovieHolder?, position: Int) {
        val title = getItem(position)

        holder?.setTitle(title)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CachedMovieHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.cached_movies_recycler_item, parent, false)

        return CachedMovieHolder(view)
    }

    fun fillAdapter(items: List<String>?) {
        mItems.fill(with = items, reset = false)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mItems.clear()
        notifyDataSetChanged()
    }

    fun getItem(position: Int): String? {
        return mItems.getOrNull(index = position)
    }
}