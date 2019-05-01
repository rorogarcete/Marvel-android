package com.bytemain.marvel.app.models

import com.bytemain.marvel.app.data.db.entity.Character

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
data class CharacterData(
        val offset: Int,
        val limit: Int,
        val total: Int,
        val count: Int,
        var results: MutableList<Character>
)