package com.example.filip.movielist

import android.app.Application

/**
 * Created by Filip Babic @cobe
 */
class App : Application() {

    companion object {
        internal lateinit var component: AppComponent

        internal lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        component.inject(this)
    }
}