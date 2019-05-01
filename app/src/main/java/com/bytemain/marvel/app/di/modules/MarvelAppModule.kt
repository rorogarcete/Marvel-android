package com.bytemain.marvel.app.di.modules

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.bytemain.marvel.app.data.db.MarvelDatabase
import com.bytemain.marvel.app.data.db.dao.CharacterDao
import com.bytemain.marvel.app.data.db.dao.ItemDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
@Module
class MarvelAppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): MarvelDatabase {
        return Room.databaseBuilder(application.applicationContext, MarvelDatabase::class.java, "MarvelDB.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: MarvelDatabase): CharacterDao {
        return database.characterDao()
    }

    @Provides
    @Singleton
    fun provideItemDao(database: MarvelDatabase): ItemDao {
        return database.itemDao()
    }

    @Singleton
    @Provides
    fun providePreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

}