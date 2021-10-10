package com.marvellist.domain.exception

import com.marvellist.domain.EXCEPTION_CODE_NOT_FOUND

class NotFoundException(
    code: Int= EXCEPTION_CODE_NOT_FOUND,
    title: String="Error",
    description: String="Not found"
) : BaseException(code, title, description)