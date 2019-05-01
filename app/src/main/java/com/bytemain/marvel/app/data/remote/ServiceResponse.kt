package com.bytemain.marvel.app.data.remote

import android.util.Log
import retrofit2.Response
import java.io.IOException

/**
 * @author rorogarcete
 * @version 0.0.1
 * Copyright 2019 Bytemain Inc. All rights reserved
 */
class ServiceResponse<T> {

    val code: Int
    val body: T?
    val error: Throwable?

    val isSuccessful: Boolean
        get() = code in 200..299


    constructor(error: Throwable) {
        code = 500
        body = null
        this.error = error
    }

    constructor(response: Response<T>) {
        code = response.code()
        if (response.isSuccessful) {
            body = response.body()
            error = null
        } else {
            var message: String? = null
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody()!!.string()
                } catch (ignored: IOException) {
                    Log.e("ERROR", "error while parsing response", ignored)
                }

            }
            if (message == null || message.trim { it <= ' ' }.isEmpty()) {
                message = response.message()
            }
            error = IOException(message)
            body = null
        }
    }
}