package com.bytemain.marvel.app.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.bytemain.marvel.app.data.db.MarvelDatabase
import com.bytemain.marvel.app.data.db.dao.CharacterDao
import com.bytemain.marvel.app.data.db.entity.Character
import com.bytemain.marvel.app.utilities.*
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
class CharacterDaoTest {

    private lateinit var database: MarvelDatabase
    private lateinit var characterDao: CharacterDao

    private val characterA = Character(1, "characterA", "description", Date(),
            thumbnail, "", comics, series, stories, events, urls, 0)

    private val characterB = Character(2, "characterB", "description", Date(),
            thumbnail, "", comics, series, stories, events, urls, 1)

    private val characterC = Character(3, "characterC", "description", Date(),
            thumbnail, "", comics, series, stories, events, urls, 2)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before fun createDB() {
        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, MarvelDatabase::class.java).build()
        characterDao = database.characterDao()
        characterDao.insertCharacters(listOf(characterC, characterA, characterB))
    }

    @After fun closeDB() {
        database.close()
    }

    @Test fun getCharactersOrderedByPage() {
        val characterList = getValue(characterDao.getCharacters())

        assertThat(characterList.size, equalTo(3))
        assertThat(characterList[0] , equalTo(characterA))
        assertThat(characterList[1] , equalTo(characterB))
        assertThat(characterList[2] , equalTo(characterC))
    }


}