package com.bytemain.marvel.app.di.modules

import com.bytemain.marvel.app.ui.characters.detail.DetailActivity
import com.bytemain.marvel.app.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun contributeDetailActivity(): DetailActivity

}