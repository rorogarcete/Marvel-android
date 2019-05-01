package com.bytemain.marvel.app.data.local

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
@Singleton
class PreferenceManager @Inject constructor(private val preferences: SharedPreferences){

    companion object {
        //Keys
        @JvmStatic val DOMINANT_COLOR = "DOMINANT_COLOR"
        @JvmStatic val SESSION_LOGIN = "SESSION_LOGIN"
        @JvmStatic val SESSION_USER = "SESSION_USER"
    }

    fun setString(key: String, value: String) {
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String, defValue: String): String? {
        return preferences.getString(key, defValue)
    }


    fun setInt(key: String, value: Int) {
        val editor = preferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String, defValue: Int): Int {
        return preferences.getInt(key, defValue)
    }


    fun setFloat(key: String, value: Float) {
        val editor = preferences.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    fun getFloat(key: String, defValue: Int): Float {
        return preferences.getFloat(key, defValue.toFloat())
    }


    fun setBoolean(key: String, value: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return preferences.getBoolean(key, defValue)
    }
}