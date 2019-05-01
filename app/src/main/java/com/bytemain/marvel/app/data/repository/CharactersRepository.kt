package com.bytemain.marvel.app.data.repository

import androidx.lifecycle.LiveData
import com.bytemain.marvel.app.data.db.dao.CharacterDao
import com.bytemain.marvel.app.data.db.entity.Character
import com.bytemain.marvel.app.data.remote.ServiceResponse
import com.bytemain.marvel.app.data.remote.MarvelService
import com.bytemain.marvel.app.data.remote.Resource
import com.bytemain.marvel.app.helpers.Utils
import com.bytemain.marvel.app.models.CharacterResponse
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
@Singleton
class CharactersRepository @Inject constructor(val characterDao: CharacterDao, val marvelService: MarvelService) {

    val defaultLimit = 20

    var offset = 0

    val timestamp = Date().time

    val hash = Utils.md5(timestamp.toString() + Utils.MARVEL_PRIVATE_KEY + Utils.MARVEL_PUBLIC_KEY)

    fun getCharacters(page: Int): LiveData<Resource<List<Character>>> {

        return object : NetworkBoundResource<List<Character>, CharacterResponse>() {

            override fun saveFetchData(item: CharacterResponse) {

                offset += defaultLimit
                val newCharacters = item.data.results

                newCharacters.forEach { character -> character.page = page }

                characterDao.insertCharacters(newCharacters)
            }

            override fun shouldFetch(data: List<Character>?): Boolean {
                if(data != null && data.isNotEmpty()) {
                    offset = data.size
                }
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Character>> {
                return if(page == 0) {
                    characterDao.getCharacters()
                } else  {
                    characterDao.getCharacters(page)
                }

            }

            override fun fetchService(): LiveData<ServiceResponse<CharacterResponse>> {
                return marvelService.getCharacters("-modified", timestamp.toString(), Utils.MARVEL_PUBLIC_KEY, hash, offset, defaultLimit)
            }

            override fun onFetchFailed() {

            }

        }.asLiveData

    }

}
