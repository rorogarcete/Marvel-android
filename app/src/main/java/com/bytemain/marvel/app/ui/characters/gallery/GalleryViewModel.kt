package com.bytemain.marvel.app.ui.characters.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bytemain.marvel.app.data.remote.ServiceResponse
import com.bytemain.marvel.app.data.repository.CharactersRepository
import com.bytemain.marvel.app.data.repository.ComicsRepository
import com.bytemain.marvel.app.models.DetailResponse
import javax.inject.Inject

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
class GalleryViewModel @Inject
constructor(private val charactersRepository: CharactersRepository, private val comicsRepository: ComicsRepository) : ViewModel() {

    lateinit var section : String

    var characterId : Int = 0

    lateinit var characterName : String

    fun getItems(characterId: Int, type: String): LiveData<ServiceResponse<DetailResponse>> {

        when (type) {
            "Comics" -> {
                return comicsRepository.getComicsByCharacterId(characterId)
            }
        }

        return MutableLiveData()
    }

}