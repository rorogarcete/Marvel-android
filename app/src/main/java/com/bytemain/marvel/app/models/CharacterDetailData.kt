package com.bytemain.marvel.app.models

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
data class CharacterDetailData(
        val offset: Int,
        val limit: Int,
        val total: Int,
        val count: Int,
        var results: MutableList<Detail>
)