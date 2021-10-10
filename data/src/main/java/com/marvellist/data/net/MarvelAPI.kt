package com.marvellist.data.net

import com.marvellist.data.net.model.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MarvelAPI {
    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Accept-Charset: utf-8"
    )
    @GET("/asda/aasd")
    fun getCharacterById(@Query("id") id: String): Deferred<ResponseCharacterMarvelApi>

}