package com.bytemain.marvel.app.ui.main

import android.app.ActivityOptions
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dagger.android.AndroidInjection
import javax.inject.Inject
import android.content.Intent
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bytemain.marvel.app.R
import com.bytemain.marvel.app.data.db.entity.Character
import com.bytemain.marvel.app.data.remote.Resource
import com.bytemain.marvel.app.data.remote.StatusResponse
import com.bytemain.marvel.app.databinding.ActivityHomeBinding
import com.bytemain.marvel.app.helpers.ViewModelFactory
import com.bytemain.marvel.app.helpers.ErrorDialog
import com.bytemain.marvel.app.helpers.Utils
import com.bytemain.marvel.app.ui.characters.list.CharacterViewHolder
import com.bytemain.marvel.app.ui.characters.list.CharacterAdapter
import com.bytemain.marvel.app.ui.characters.detail.DetailActivity
import kotlinx.android.synthetic.main.item_character.view.*
import android.util.Pair as UtilPair

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
class MainActivity: AppCompatActivity(), CharacterViewHolder.Delegate {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val binding by lazy { DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home) }
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.firstTime = true
            setupInit()
            observeViewModel()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.user_session -> {
                msgShow(viewModel.getUserSession())
                return true
            }
            R.id.show_characters -> {
                msgShow("Show Characters")
                return true
            }
            R.id.user_logout -> {
                viewModel.logoutUser()
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }

    fun msgShow(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onItemClick(character: Character, view: View) {
        val img = UtilPair.create(view.image as View, resources.getString(R.string.transition_character_image))
        val name = UtilPair.create(view.name as View, resources.getString(R.string.transition_character_name))
        val options = ActivityOptions.makeSceneTransitionAnimation(this, img , name)

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.intent_character , character as Parcelable)
        startActivity(intent, options.toBundle())
    }

    //Local Methods
    private fun setupInit() {
        val linearLayout = androidx.recyclerview.widget.LinearLayoutManager(this)
        binding.rvCharacters.layoutManager = linearLayout
        viewModel.adapter = CharacterAdapter(this)
        binding.rvCharacters.adapter = viewModel.adapter
        binding.rvCharacters.addOnScrollListener(Utils.InfiniteScrollListener({
            viewModel.pageCounter += 1
            loadMore(viewModel.pageCounter) }, linearLayout))
    }

    private fun observeViewModel() {
        viewModel.charactersLiveData.observe(this, Observer { it?.let { processResponse(it) } })
        loadMore(viewModel.pageCounter++)
    }

    private fun loadMore(page : Int) {
        viewModel.postPage(page)
    }

    private fun processResponse(response: Resource<List<Character>>) {
        when (response.status) {
            StatusResponse.LOADING -> binding.progressBar.visibility = View.VISIBLE
            StatusResponse.SUCCESS -> {
                renderDataState(Utils.checkCharactersImages(response.data!!))
            }
            StatusResponse.ERROR -> {
                binding.progressBar.visibility = View.GONE
                ErrorDialog.show(this.supportFragmentManager.beginTransaction(), response.error.toString())
            }
        }
    }

    private fun renderDataState(items : List<Character>) {
        binding.progressBar.visibility = View.GONE

        viewModel.pageCounter = items[items.size-1].page
        viewModel.adapter.updateList(items)

        if(viewModel.firstTime) {
            binding.rvCharacters.scheduleLayoutAnimation()
            viewModel.firstTime = false
        }
    }

}
