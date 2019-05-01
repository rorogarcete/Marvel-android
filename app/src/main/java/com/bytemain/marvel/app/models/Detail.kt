package com.bytemain.marvel.app.models

import android.os.Parcelable
import com.bytemain.marvel.app.data.db.entity.Item
import kotlinx.android.parcel.Parcelize

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
@Parcelize
data class Detail(
        val id: Int,
        val digitalId: String?,
        val title: String?,
        val issueNumber: String?,
        val variantDescription: String?,
        val description: String?,
        val modified: String?,
        val isbn: String?,
        val upc: String?,
        val diamondCode: String?,
        val ean: String?,
        val issn: String?,
        val format: String?,
        val pageCount: Int,
        val series: Item?,
        val dates: MutableList<Date>?,
        val prices: MutableList<Price>?,
        val creators: CollectionItem?,
        val characters: CollectionItem?,
        val resourceURI: String,
        val thumbnail: Thumbnail?,
        val images: MutableList<Thumbnail>?,
        val urls: MutableList<ItemUrl>?
): Parcelable