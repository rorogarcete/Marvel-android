package com.bytemain.marvel.app.data.db.dao

import androidx.lifecycle.LiveData
import com.bytemain.marvel.app.data.db.entity.Character
import androidx.room.*

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: Character)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<Character>)

    @Update
    fun updateCharacters(characters: List<Character>)

    @Query("SELECT * FROM Character WHERE page = :page")
    fun getCharacters(page : Int): LiveData<List<Character>>


    @Query("SELECT * FROM Character ORDER BY page")
    fun getCharacters(): LiveData<List<Character>>

    @Query("SELECT * FROM Character WHERE name = :name")
    fun getCharacterByName(name: String) : Character

    @Update
    fun updateCharacter(character: Character)

    @Query("DELETE FROM Character WHERE id = :id")
    fun deleteCharacter(id: Int)

}