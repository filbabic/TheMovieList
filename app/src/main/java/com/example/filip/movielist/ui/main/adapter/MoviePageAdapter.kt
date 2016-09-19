package com.example.filip.movielist.ui.main.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.*

/**
 * Created by Filip Babic @cobe
 */
class MoviePageAdapter constructor(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val movieFragments: MutableList<Fragment> = ArrayList()
    private val fragmentTitles: MutableList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return movieFragments.get(position)
    }

    override fun getCount(): Int {
        return movieFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragmentTitles.getOrElse(position, { "" })
    }

    fun addItem(fragment: Fragment, title: String) {
        movieFragments.add(fragment)
        fragmentTitles.add(title)
        notifyDataSetChanged()
    }
}