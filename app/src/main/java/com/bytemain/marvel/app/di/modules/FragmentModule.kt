package com.bytemain.marvel.app.di.modules

import com.bytemain.marvel.app.ui.characters.gallery.GalleryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contributeGalleryFragment(): GalleryFragment
}