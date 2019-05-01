package com.bytemain.marvel.app.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bytemain.marvel.app.data.db.entity.Item

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(items: List<Item>)

    @Query("SELECT * FROM Item")
    fun getItems(): LiveData<List<Item>>

    @Query("SELECT * FROM Item WHERE type = :type")
    fun getItemsByType(type: String): LiveData<List<Item>>
}