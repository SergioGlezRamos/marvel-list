package com.marvellist.domain.exception

import com.marvellist.domain.EXCEPTION_CODE_INTERNAL_SERVER

class InternalServerException(
    code: Int= EXCEPTION_CODE_INTERNAL_SERVER,
    title: String="Error",
    description: String="A unknown error has been produced"
) : BaseException(code, title, description)