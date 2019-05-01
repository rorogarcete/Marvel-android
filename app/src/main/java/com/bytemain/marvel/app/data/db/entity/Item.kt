package com.bytemain.marvel.app.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
@Entity(primaryKeys = ["resourceURI"])
@Parcelize
data class Item(
        val resourceURI: String,
        val name: String,
        val type: String?,
        val role : String?
): Parcelable