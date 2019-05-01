package com.bytemain.marvel.app.ui.base

import android.content.Context
import android.view.View

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
abstract class BaseViewHolder(private val view : View):
        androidx.recyclerview.widget.RecyclerView.ViewHolder(view),
        View.OnClickListener, View.OnLongClickListener {

    init {
        view.setOnClickListener(this)
        view.setOnLongClickListener(this)
    }

    @Throws(Exception::class)
    abstract fun bindData(data: Any?)

    protected fun view(): View {
        return view
    }

    protected fun context(): Context {
        return view.context
    }
}