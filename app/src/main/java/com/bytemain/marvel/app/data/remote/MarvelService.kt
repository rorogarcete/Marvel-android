package com.bytemain.marvel.app.data.remote

import androidx.lifecycle.LiveData
import com.bytemain.marvel.app.models.CharacterResponse
import com.bytemain.marvel.app.models.DetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
interface MarvelService {

    @GET("/v1/public/characters")
    fun getCharacters(
            @Query("orderBy") modified: String,
             @Query("ts") ts: String,
             @Query("apikey") apiKey: String,
             @Query("hash") hash: String,
             @Query("offset") offset: Int,
             @Query("limit") limit: Int): LiveData<ServiceResponse<CharacterResponse>>

    @GET("/v1/public/characters/{id}")
    fun getCharacterDetail(@Path("id") id: String,
              @Query("ts") ts: String,
              @Query("apikey") apiKey: String,
              @Query("hash") hash: String): LiveData<ServiceResponse<CharacterResponse>>

    @GET("/v1/public/characters/{characterId}/comics")
    fun getComicsByCharacterId(@Path("characterId") characterId: String,
              @Query("apikey") apiKey: String,
              @Query("hash") hash: String,
              @Query("ts") ts: String,
              @Query("orderBy") orderBy: String): LiveData<ServiceResponse<DetailResponse>>

}