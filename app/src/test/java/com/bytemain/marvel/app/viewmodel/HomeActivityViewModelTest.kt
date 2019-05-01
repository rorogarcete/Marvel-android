package com.bytemain.marvel.app.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModelProviders
import com.bytemain.marvel.app.api.LiveDataTestUtil
import com.bytemain.marvel.app.base.BaseTest
import com.bytemain.marvel.app.ui.main.MainViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import java.net.HttpURLConnection
import org.hamcrest.MatcherAssert
import org.hamcrest.CoreMatchers.notNullValue
import org.mockito.ArgumentMatchers.anyInt
import kotlin.test.assertEquals

@RunWith(RobolectricTestRunner::class)
class HomeActivityViewModelTest : BaseTest() {

    private lateinit var activity : AppCompatActivity

    private lateinit var viewModel : MainViewModel

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    override fun setUp() {
        super.setUp()
        this.activity = Robolectric.setupActivity(AppCompatActivity::class.java)
        this.viewModel = ViewModelProviders.of(this.activity , viewModelFactory)[MainViewModel::class.java]
    }


    override fun isMockServerEnabled(): Boolean {
       return true
    }

    //TESTS
    @Test
    fun getCharacters_Success() {
        // Prepare data
        this.mockHttpResponse("get-characters.json", HttpURLConnection.HTTP_OK)
        //Pre Test
        MatcherAssert.assertThat(this.viewModel.charactersLiveData.value , notNullValue())

        //Execute view model, change the page number
        this.viewModel.postPage(anyInt())

        val characters = LiveDataTestUtil.getValue(this.viewModel.charactersLiveData).data

        MatcherAssert.assertThat(characters, notNullValue())

        assertEquals(10 , characters?.size)
    }
}