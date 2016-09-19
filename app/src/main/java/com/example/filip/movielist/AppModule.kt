package com.example.filip.movielist

import android.content.Context
import com.example.filip.movielist.constants.Constants
import com.example.filip.movielist.data.preference.PreferenceHelper
import com.example.filip.movielist.data.preference.PreferenceHelperImpl
import dagger.Module
import dagger.Provides
import io.realm.RealmConfiguration
import javax.inject.Singleton

/**
 * Created by Filip Babic @cobe
 */
@Module
class AppModule constructor(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideRealmConfiguration(): RealmConfiguration {
        return RealmConfiguration.Builder(context).build()
    }

    @Provides
    fun providePreferenceHelper(context: Context): PreferenceHelper {
        return PreferenceHelperImpl(context.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE))
    }
}