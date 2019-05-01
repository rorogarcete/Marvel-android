package com.bytemain.marvel.app

import android.app.Activity
import android.app.Application
import com.bytemain.marvel.app.di.components.DaggerMarvelAppComponent
import com.bytemain.marvel.app.helpers.Utils
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * @author rorogarcete
 * @version 0.0.1
 * Application Class
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
class MarvelApplication: Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    override fun onCreate() {
        super.onCreate()
        initGraph()
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment> = fragmentInjector

    private fun initGraph() {
        DaggerMarvelAppComponent.builder()
                .application(this)
                .baseUrl(Utils.BASE_URL)
                .build().inject(this)
    }

}