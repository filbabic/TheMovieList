package com.example.filip.movielist.ui.username

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.ViewSwitcher
import com.example.filip.movielist.App
import com.example.filip.movielist.R
import com.example.filip.movielist.common.extensions.toast
import com.example.filip.movielist.data.preference.PreferenceHelper
import com.example.filip.movielist.presentation.UsernameChangePresenter
import com.example.filip.movielist.ui.base.BaseActivity
import com.example.filip.movielist.view.UsernameChangeView
import javax.inject.Inject

/**
 * Created by Filip Babic @cobe
 */
class UsernameChangeActivity : BaseActivity(), UsernameChangeView {

    private lateinit var toolbar: Toolbar
    private lateinit var viewSwitcher: ViewSwitcher
    private lateinit var userNameEditText: EditText
    private lateinit var userNameTextView: TextView
    private lateinit var saveUsernameButton: Button

    @Inject lateinit var usernameChangePresenter: UsernameChangePresenter

    @Inject lateinit var preferenceHelper: PreferenceHelper

    companion object {

        fun getLaunchIntent(from: Context): Intent {
            return Intent(from, UsernameChangeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_username_change)
        App.component.inject(this)
        prepareData()
        initUI()
    }

    override fun initUI() {
        toolbar = findViewById(R.id.toolbar) as Toolbar
        viewSwitcher = findViewById(R.id.username_change_view_switcher) as ViewSwitcher
        userNameEditText = findViewById(R.id.username_change_edit_text) as EditText
        userNameTextView = findViewById(R.id.username_change_text_view) as TextView
        saveUsernameButton = findViewById(R.id.username_change_button) as Button

        userNameTextView.text = preferenceHelper.getUsername()

        saveUsernameButton.setOnClickListener { changeUsername() }
        viewSwitcher.setOnClickListener { switchViews() }
    }

    private fun changeUsername() {
        usernameChangePresenter.changeUsername(userNameEditText.text.toString())
    }

    private fun switchViews() {
        usernameChangePresenter.switchViews()
    }

    override fun prepareData() {
        usernameChangePresenter.setView(this)
    }

    override fun saveUsername(username: String) {
        preferenceHelper.saveUsername(username)
    }

    override fun animateSwitchViews() {
        viewSwitcher.showNext()
    }

    override fun showInvalidUsernameError() {
        userNameEditText.error = getString(R.string.username_change_error_message)
    }

    override fun showSuccessMessage() {
        toast(message = getString(R.string.username_change_error_message))
        finish()
    }

    override fun onRequestsCancelled() {
    }
}