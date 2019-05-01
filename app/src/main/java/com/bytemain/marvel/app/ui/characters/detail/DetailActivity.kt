package com.bytemain.marvel.app.ui.characters.detail

import android.graphics.Bitmap
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.bytemain.marvel.app.R
import com.bytemain.marvel.app.databinding.ActivityDetailCircularBinding
import com.bytemain.marvel.app.helpers.ViewModelFactory
import com.bytemain.marvel.app.helpers.Utils
import com.bytemain.marvel.app.ui.characters.gallery.GalleryFragment
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java) }

    private val binding by lazy { DataBindingUtil.setContentView<ActivityDetailCircularBinding>(this, R.layout.activity_detail_circular) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        if(savedInstanceState == null) {
            supportPostponeEnterTransition()
            initUI()
        }
    }

    private fun initUI() {
        getCharacterFromIntent()

        binding.character = viewModel.character

        val requestOptions = RequestOptions()
        requestOptions.circleCrop()

        Glide.with(this)
                .asBitmap()
                .apply(requestOptions)
                .load(getImageUri())
                .listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                        supportStartPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        supportStartPostponedEnterTransition()
                        Palette.from(resource!!).generate {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                val dominantColor = it?.getDominantColor(resources.getColor(R.color.black, null))!!
                                val colors: IntArray = intArrayOf(resources.getColor(R.color.black, null), dominantColor)
                                viewModel.saveDominantColor(dominantColor)
                                val gradientDrawable = GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, colors)
                                gradientDrawable.cornerRadius = 0f
                                //binding.viewGradient.backgroundDrawable = gradientDrawable
                            } else {
                                it?.getDominantColor(resources.getColor(R.color.black))
                            }
                        }

                        return false
                    }
                }).into(binding.circularImage)

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        Utils.addFragmentToActivity(supportFragmentManager, GalleryFragment.newInstance("Comics", viewModel.character.id, viewModel.character.name), binding.containerComics.id)
    }

    companion object {
        const val TAG = "DetailActivity"
        const val intent_character = "character"
        const val IMAGE_TYPE = "."
    }

    private fun getImageUri(): String {
        return viewModel.character.thumbnail.path + IMAGE_TYPE + viewModel.character.thumbnail.extension
    }

    private fun getCharacterFromIntent() {
        viewModel.character = intent.extras.getParcelable(intent_character)
    }

}