package com.bytemain.marvel.app.data.remote

/**
 * @author rorogarcete
 * @version 0.0.1
 * @param <T>
 * A generic class that holds a value with its loading status.
 * I use this class with NetworkBoundResource cause i want to handle the states : Loading , Success, Error
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
data class Resource<T> private constructor(val status: StatusResponse, val data: T?, val error: Throwable?) {
    companion object {

        fun <T> loading(data: T?): Resource<T> {
            return Resource(StatusResponse.LOADING, data, null)
        }
        fun <T> success(data: T?): Resource<T> {
            return Resource(StatusResponse.SUCCESS, data, null)
        }

        fun <T> error(exception: Throwable?): Resource<T> {
            return Resource(StatusResponse.ERROR, null, exception)
        }
    }
}