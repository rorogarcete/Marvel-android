package com.bytemain.marvel.app.ui.characters.detail

import android.view.View
import com.bytemain.marvel.app.R
import com.bytemain.marvel.app.models.Detail
import com.bytemain.marvel.app.ui.base.BaseAdapter
import com.bytemain.marvel.app.ui.base.BaseViewHolder

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
class DetailAdapter(val delegate : DetailViewHolder.Delegate) : BaseAdapter(){

    init {
        addItems(ArrayList<Detail>())
    }

    fun updateList(newItems : List<Detail>) {
        addItems(newItems)
        notifyItemInserted(items.size)
    }

    override fun layout(item: Any?): Int {
        return R.layout.item_small_image
    }

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        return DetailViewHolder(view , delegate)
    }
}