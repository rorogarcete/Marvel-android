package com.bytemain.marvel.app.ui.characters.list

import android.view.View
import com.bytemain.marvel.app.R
import com.bytemain.marvel.app.data.db.entity.Character
import com.bytemain.marvel.app.ui.base.BaseAdapter
import com.bytemain.marvel.app.ui.base.BaseViewHolder

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
class CharacterAdapter(val delegate: CharacterViewHolder.Delegate) : BaseAdapter() {

    init {
        addItems(ArrayList<Character>())
    }

    fun updateList( characters : List<Character>) {
        addItems(characters)
        notifyItemInserted(items.size)
    }

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        return CharacterViewHolder(view, delegate)
    }

    override fun layout(item: Any?): Int {
        return if(item == null) R.layout.item_loading else R.layout.item_character
    }


}