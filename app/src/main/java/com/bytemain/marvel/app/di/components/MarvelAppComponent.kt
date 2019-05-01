package com.bytemain.marvel.app.di.components

import android.app.Application
import com.bytemain.marvel.app.MarvelApplication
import com.bytemain.marvel.app.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class),
    (ActivityModule::class),
    (FragmentModule::class),
    (ViewModelModule::class),
    (MarvelAppModule::class),
    (NetworkModule::class)])
interface MarvelAppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun baseUrl(url : String) : Builder

        fun build(): MarvelAppComponent
    }

    fun inject(instance: MarvelApplication)
}