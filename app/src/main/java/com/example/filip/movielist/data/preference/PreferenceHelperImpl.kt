package com.example.filip.movielist.data.preference

import android.content.SharedPreferences
import com.example.filip.movielist.constants.Constants

/**
 * Created by Filip Babic @cobe
 */
class PreferenceHelperImpl constructor(private val sharedPreferences: SharedPreferences) : PreferenceHelper {

    override fun getUsername(): String {
        return sharedPreferences.getString(Constants.PREFS_KEY_USERNAME, Constants.DEFAULT_USERNAME)
    }

    override fun saveUsername(username: String) {
        sharedPreferences.edit().putString(Constants.PREFS_KEY_USERNAME, username).apply()
    }
}