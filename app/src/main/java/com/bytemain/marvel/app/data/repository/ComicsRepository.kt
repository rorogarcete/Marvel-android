package com.bytemain.marvel.app.data.repository

import androidx.lifecycle.LiveData
import com.bytemain.marvel.app.data.remote.ServiceResponse
import com.bytemain.marvel.app.data.remote.MarvelService
import com.bytemain.marvel.app.helpers.Utils
import com.bytemain.marvel.app.models.DetailResponse
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
@Singleton
class ComicsRepository @Inject constructor(private val marvelApi: MarvelService) {

    private val timestamp = Date().time

    private val hash = Utils.md5(timestamp.toString() + Utils.MARVEL_PRIVATE_KEY + Utils.MARVEL_PUBLIC_KEY)

    fun getComicsByCharacterId(id : Int) : LiveData<ServiceResponse<DetailResponse>> {
        return marvelApi.getComicsByCharacterId(id.toString(), Utils.MARVEL_PUBLIC_KEY, hash, timestamp.toString(), "-onsaleDate")
    }

}