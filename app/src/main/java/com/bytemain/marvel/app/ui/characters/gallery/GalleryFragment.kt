package com.bytemain.marvel.app.ui.characters.gallery

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bytemain.marvel.app.R
import com.bytemain.marvel.app.data.remote.ServiceResponse
import com.bytemain.marvel.app.databinding.FragmentHorizontalGalleryBinding
import com.bytemain.marvel.app.helpers.ViewModelFactory
import com.bytemain.marvel.app.helpers.Utils
import com.bytemain.marvel.app.models.Detail
import com.bytemain.marvel.app.models.DetailResponse
import com.bytemain.marvel.app.ui.characters.detail.DetailAdapter
import com.bytemain.marvel.app.ui.characters.detail.DetailViewHolder
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
class GalleryFragment: Fragment(), DetailViewHolder.Delegate {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {ViewModelProviders.of(this, viewModelFactory).get(GalleryViewModel::class.java)}

    lateinit var binding: FragmentHorizontalGalleryBinding
    lateinit var adapter: DetailAdapter

    companion object {
        private const val TITLE = "title"
        private const val CHARACTER_ID = "characterId"
        private const val CHARACTER_NAME = "character_name"

        fun newInstance(title : String , characterId: Int, characterName: String): GalleryFragment {
            val args = Bundle()
            args.putString(TITLE , title)
            args.putInt(CHARACTER_ID, characterId)
            args.putString(CHARACTER_NAME, characterName)
            val fragment = GalleryFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater ,  R.layout.fragment_horizontal_gallery, container, false)
        initView()
        return binding.root
    }

    override fun onItemClick(item: Detail, view: View) { }

    //Local Methods
    private fun initView() {
        viewModel.characterId = arguments!!.getInt(CHARACTER_ID)
        adapter = DetailAdapter(this)
        val linearLayoutManager = LinearLayoutManager( context, LinearLayoutManager.HORIZONTAL, false)
        binding.listItems.layoutManager = linearLayoutManager
        binding.listItems.adapter = adapter
        binding.sectionTitle.text = arguments!!.getString(TITLE)
        viewModel.section =  arguments!!.getString(TITLE)
        viewModel.characterName = arguments!!.getString(CHARACTER_NAME)
    }

    private fun observeViewModel() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getItems(viewModel.characterId, viewModel.section).observe(this, Observer { it?.let { processResponse(it) } })
    }

    private fun processResponse(response: ServiceResponse<DetailResponse>) {
        binding.progressBar.visibility = View.GONE
        if(response.isSuccessful && response.body != null) {
            renderDataState(Utils.checkDetailsImages(response.body.data.results))
        }
    }

    private fun renderDataState ( items : List<Detail>) {
        adapter.updateList(items)
    }

}