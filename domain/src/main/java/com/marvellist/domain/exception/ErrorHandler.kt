package com.marvellist.domain.exception

interface ErrorHandler {

    fun getException(code:Int):Throwable?
    fun getVerificationLoginException(code: String): Throwable?
}