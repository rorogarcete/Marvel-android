package com.bytemain.marvel.app.data.db

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import com.bytemain.marvel.app.data.db.entity.Item
import com.bytemain.marvel.app.models.ItemUrl
import java.util.Date
import java.util.Collections

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
class MarvelConverters {

    var gson = Gson()

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {

        return when (value) {
            null -> null
            else -> Date(value)
        }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {

        return when (date) {
            null -> null
            else -> date.time
        }
    }

    @TypeConverter
    fun stringToItemUrlList(data: String?): List<ItemUrl> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<ItemUrl>>() {

        }.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun itemUrlListToString(someObjects: List<ItemUrl>): String {
        return gson.toJson(someObjects)
    }

    @TypeConverter
    fun stringToItemList(data: String?): List<Item> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Item>>() {

        }.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun itemListToString(items: List<Item>): String {
        return gson.toJson(items)
    }
}