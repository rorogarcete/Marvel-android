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
data class CollectionItem(
        val available: Int,
        val collectionURI: String,
        val items: MutableList<Item>,
        val returned: Int
): Parcelable