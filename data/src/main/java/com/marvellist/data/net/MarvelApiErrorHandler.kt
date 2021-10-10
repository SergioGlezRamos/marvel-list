package com.marvellist.data.net

import com.marvellist.domain.EXCEPTION_CODE_BAD_REQUEST
import com.marvellist.domain.EXCEPTION_CODE_NOT_FOUND
import com.marvellist.domain.exception.BadRequestException
import com.marvellist.domain.exception.ErrorHandler
import com.marvellist.domain.exception.InternalServerException
import com.marvellist.domain.exception.NotFoundException

class MarvelApiErrorHandler : ErrorHandler {

    override fun getException(code: Int): Throwable? {
        return when (code) {
            EXCEPTION_CODE_BAD_REQUEST -> BadRequestException(
                title = "Error title",
                description = "Bad request error"
            )
            EXCEPTION_CODE_NOT_FOUND -> NotFoundException(
                title = "Error title",
                description = "Not found error"
            )
            else -> InternalServerException(
                title = "Error title",
                description = "Internal server error"
            )
        }
    }
}