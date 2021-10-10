package com.marvellist.domain.exception

interface ErrorHandler {

    fun getException(code:Int):Throwable?
}