package com.bytemain.marvel.app.data.repository

import com.bytemain.marvel.app.data.local.PreferenceManager
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
@Singleton
class MainRepository @Inject constructor(private val preferenceManager: PreferenceManager) {

    fun saveDominantColor(color : Int) {
        preferenceManager.setInt(PreferenceManager.DOMINANT_COLOR, color)
    }
}