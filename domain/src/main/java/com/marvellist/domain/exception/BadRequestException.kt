package com.marvellist.domain.exception

import com.marvellist.domain.EXCEPTION_CODE_BAD_REQUEST

class BadRequestException(
    code: Int = EXCEPTION_CODE_BAD_REQUEST,
    title: String="Bad request error",
    description: String="The request has been invalidated. It could be expired"
) : BaseException(code, title, description)