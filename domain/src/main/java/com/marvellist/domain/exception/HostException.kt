package com.marvellist.domain.exception

import java.io.IOException

class HostException(
    message: String = "Unknown host. The url could be bad written," +
            " or you are using an VPN that could be generating problems"
) : IOException(message)