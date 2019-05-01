package com.bytemain.marvel.app.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bytemain.marvel.app.data.db.entity.Character
import com.bytemain.marvel.app.data.local.PreferenceManager
import com.bytemain.marvel.app.data.remote.Resource
import com.bytemain.marvel.app.data.repository.CharactersRepository
import com.bytemain.marvel.app.ui.characters.list.CharacterAdapter
import javax.inject.Inject

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
class MainViewModel @Inject constructor(
        private val charactersRepository: CharactersRepository,
        private val preferenceManager: PreferenceManager): ViewModel() {

    var charactersLiveData: LiveData<Resource<List<Character>>> = MutableLiveData()

    lateinit var adapter : CharacterAdapter

    var firstTime = false
    var pageCounter = 0

    private val page: MutableLiveData<Int> = MutableLiveData()

    init {
        charactersLiveData = Transformations.switchMap(page) { charactersRepository.getCharacters(page.value!!) }
    }

    fun postPage(page: Int) { this.page.value = page }

    fun logoutUser() {
        preferenceManager.setInt(PreferenceManager.SESSION_LOGIN, 0)
    }

    fun getUserSession(): String {
        return preferenceManager.getString(PreferenceManager.SESSION_USER, "")!!
    }

}