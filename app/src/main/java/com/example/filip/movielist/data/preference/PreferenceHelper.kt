package com.example.filip.movielist.data.preference

/**
 * Created by Filip Babic @cobe
 */
interface PreferenceHelper {

    fun saveUsername(username: String)

    fun getUsername(): String?
}