package com.bytemain.marvel.app.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import android.os.Parcelable
import com.bytemain.marvel.app.models.CollectionItem
import com.bytemain.marvel.app.models.ItemUrl
import com.bytemain.marvel.app.models.Thumbnail
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
@Entity(primaryKeys = ["id"])
@Parcelize
data class Character(
        val id: Int,
        val name: String,
        val description: String,
        val modified: Date,
        @Embedded(prefix = "thumbnail_")
        val thumbnail: Thumbnail,
        val resourceURI: String?,
        @Embedded(prefix = "comics_")
        val comics: CollectionItem,
        @Embedded(prefix = "series_")
        val series: CollectionItem,
        @Embedded(prefix = "stories_")
        val stories: CollectionItem,
        @Embedded(prefix = "events_")
        val events: CollectionItem,
        val urls: MutableList<ItemUrl>,
        var page : Int
): Parcelable