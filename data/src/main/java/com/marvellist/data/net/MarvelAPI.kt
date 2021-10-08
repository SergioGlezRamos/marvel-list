package com.marvellist.data.net

import com.marvellist.data.net.model.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelAPI {

    @GET("/url")
    fun getCharacterById(@Query("id") id: String): Deferred<ResponseCharacterMarvelApi>

}