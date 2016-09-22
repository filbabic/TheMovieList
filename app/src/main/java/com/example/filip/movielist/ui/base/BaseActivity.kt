package com.example.filip.movielist.ui.base

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.filip.movielist.R

/**
 * Created by Filip Babic @cobe
 */
abstract class BaseActivity : AppCompatActivity() {

    abstract fun initUI()

    abstract fun prepareData()


    protected fun enableHomeButton() {
        val supportActionBar = supportActionBar

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finishActivity()
        return super.onOptionsItemSelected(item)
    }

    private fun finishActivity() {
        onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}