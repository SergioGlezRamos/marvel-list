package com.marvellist.domain.exception

import java.io.IOException

class NoInternetException(
    message: String = "No internet connection is available"
) : IOException(message)