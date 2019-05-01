package com.bytemain.marvel.app.ui.characters.list

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.bytemain.marvel.app.data.db.entity.Character
import com.bytemain.marvel.app.helpers.Utils

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
class CharacterItemViewModel(private val character: Character): BaseObservable() {

    companion object {
        val IMAGE_TYPE = "."
    }

    var imageUrl = modelImageUrl()

    fun modelImageUrl(): String = character.thumbnail.path + IMAGE_TYPE + character.thumbnail.extension

    @Bindable
    fun getCharacterName(): String = character.name

    @Bindable
    fun getCharacterDescription(): String = character.description

    @Bindable
    fun getCharacterModified(): String = Utils.toSimpleString(character.modified)
}