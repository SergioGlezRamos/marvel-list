package com.marvellist.data.net

import com.marvellist.domain.exception.ErrorHandler

class MarvelApiErrorHandler () : ErrorHandler {

    override fun getException(code: Int): Throwable? {
        return null
    }

    override fun getVerificationLoginException(code: String): Throwable? {
        return null
    }
}