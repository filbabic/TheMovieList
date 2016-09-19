package com.example.filip.movielist.data.database

import com.example.filip.movielist.AppModule
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by Filip Babic @cobe
 */

@Module(includes = arrayOf(AppModule::class))
class DatabaseModule {

    @Provides
    fun provideRealm(realmConfiguration: RealmConfiguration): Realm {
        return Realm.getInstance(realmConfiguration)
    }

    @Provides
    fun provideDatabaseManager(realm: Realm): DatabaseManager {
        return DatabaseManagerImpl(instance = realm)
    }
}