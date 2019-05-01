package com.bytemain.marvel.app.models

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
data class CharacterResponse(
        val code: Int,
        val status: String,
        val copyright: String,
        val attributionText: String,
        val attributionHTML: String,
        val etag: String, val data: CharacterData
)