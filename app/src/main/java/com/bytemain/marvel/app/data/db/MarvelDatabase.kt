package com.bytemain.marvel.app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bytemain.marvel.app.data.db.dao.CharacterDao
import com.bytemain.marvel.app.data.db.dao.ItemDao
import com.bytemain.marvel.app.data.db.entity.Character
import com.bytemain.marvel.app.data.db.entity.Item

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
@Database(entities = [(Character::class), (Item::class)], version = 4)
@TypeConverters(MarvelConverters::class)
abstract class MarvelDatabase: RoomDatabase() {

    abstract fun characterDao() : CharacterDao

    abstract fun itemDao() : ItemDao
}