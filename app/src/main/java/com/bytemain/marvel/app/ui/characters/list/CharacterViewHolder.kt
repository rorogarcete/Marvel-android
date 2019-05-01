package com.bytemain.marvel.app.ui.characters.list

import androidx.databinding.DataBindingUtil
import android.view.View
import com.bytemain.marvel.app.data.db.entity.Character
import com.bytemain.marvel.app.databinding.ItemCharacterBinding
import com.bytemain.marvel.app.ui.base.BaseViewHolder

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
class CharacterViewHolder(view: View, val delegate: Delegate): BaseViewHolder(view) {

    private lateinit var character: Character

    private val binding by lazy { DataBindingUtil.bind<ItemCharacterBinding>(view) }

    interface Delegate {
        fun onItemClick(character: Character, view: View)
    }

    override fun bindData(data: Any?) {
        if (data is Character) {
            character = data
            drawUI()
        }
    }

    override fun onClick(view: View?) {
        delegate.onItemClick(character, itemView)
    }

    override fun onLongClick(view: View?): Boolean {
        return false
    }

    // Local Methods
    private fun drawUI() {
        binding.apply {
            binding?.vModel = CharacterItemViewModel(character)
            binding?.executePendingBindings()
        }
    }

}