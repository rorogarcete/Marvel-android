package com.bytemain.marvel.app.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
@Parcelize
data class Thumbnail(
        val path: String,
        val extension: String
): Parcelable