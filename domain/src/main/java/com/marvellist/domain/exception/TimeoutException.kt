package com.marvellist.domain.exception

import java.io.IOException

class TimeoutException(
    message: String ="Timeout exception"
) : IOException(message)