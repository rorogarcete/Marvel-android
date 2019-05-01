package com.bytemain.marvel.app.ui.characters.detail

import androidx.lifecycle.ViewModel
import com.bytemain.marvel.app.data.db.entity.Character
import com.bytemain.marvel.app.data.repository.MainRepository
import javax.inject.Inject

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
class DetailViewModel @Inject
constructor(private val mainRepository: MainRepository) : ViewModel() {

    lateinit var character : Character

    fun saveDominantColor(color : Int) = mainRepository.saveDominantColor(color)
}