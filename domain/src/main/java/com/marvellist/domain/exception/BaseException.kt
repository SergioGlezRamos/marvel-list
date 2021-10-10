package com.marvellist.domain.exception

import java.io.IOException

abstract class BaseException(val code: Int = 0, val title: String, val description: String) : IOException(description)