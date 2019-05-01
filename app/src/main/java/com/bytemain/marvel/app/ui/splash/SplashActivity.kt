package com.bytemain.marvel.app.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.bytemain.marvel.app.R
import com.bytemain.marvel.app.ui.main.MainActivity

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            goToMainActivity()
        }, 3000)
    }

    private fun goToMainActivity() {
        startActivity( Intent(this, MainActivity::class.java))
        finish()
    }
}