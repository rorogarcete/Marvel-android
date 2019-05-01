package com.bytemain.marvel.app.ui.characters.detail

import android.view.View
import androidx.databinding.DataBindingUtil
import com.bytemain.marvel.app.databinding.ItemSmallImageBinding
import com.bytemain.marvel.app.models.Detail
import com.bytemain.marvel.app.ui.base.BaseViewHolder

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
class DetailViewHolder(view: View, val delegate: Delegate) : BaseViewHolder(view) {

    private lateinit var item: Detail
    private val binding by lazy { DataBindingUtil.bind<ItemSmallImageBinding>(view) }

    interface Delegate {
        fun onItemClick(item: Detail, view: View)
    }

    override fun bindData(data: Any?) {
        if (data is Detail) {
            item = data
            drawUI()
        }
    }

    override fun onClick(v: View?) {
        delegate.onItemClick(item, itemView)
    }

    override fun onLongClick(v: View?): Boolean {
        return false
    }

    private fun drawUI() {
        binding?.detail = item
        binding?.url = item.thumbnail?.path + "." + item.thumbnail?.extension
        binding?.executePendingBindings()
    }

}