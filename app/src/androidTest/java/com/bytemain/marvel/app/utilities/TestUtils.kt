package com.bytemain.marvel.app.utilities

import com.bytemain.marvel.app.data.db.entity.Item
import com.bytemain.marvel.app.models.CollectionItem
import com.bytemain.marvel.app.models.ItemUrl
import com.bytemain.marvel.app.models.Thumbnail

/**
 * objects used for tests.
 */

val items = mutableListOf<Item>()

val thumbnail = Thumbnail("" , "")

val comics = CollectionItem(30, "", items , 20)

val series = CollectionItem(40, "", items , 20)

val stories = CollectionItem(50, "", items , 20)

val events = CollectionItem(60, "", items , 20)

val urls = mutableListOf<ItemUrl>(ItemUrl("", ""))



