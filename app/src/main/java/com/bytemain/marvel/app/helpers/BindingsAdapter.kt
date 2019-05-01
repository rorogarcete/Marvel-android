package com.bytemain.marvel.app.helpers

import androidx.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url : String?) {

    if(url != null) {
        Glide.with(imageView.context)
                .load(url)
                .into(imageView)
    }

}

@BindingAdapter("galleryImageUrl")
fun setGalleryImageUrl(imageView: ImageView, url : String) {

    if (!url.isNullOrEmpty()) {
        Glide.with(imageView.context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
    }

}