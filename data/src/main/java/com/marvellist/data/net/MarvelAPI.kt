package com.marvellist.data.net

import com.marvellist.data.net.model.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelAPI {
    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Accept-Charset: utf-8"
    )
    @GET("/v1/public/characters/{id}")
    fun getCharacterById(@Path("id") id: String,@Query("ts") ts:  String, @Query("apikey") apiKey: String, @Query("hash") hash: String): Deferred<ResponseCharacterMarvelApi>

    @GET("/v1/public/characters")
    fun getCharacterList(@Query("ts") ts:  String, @Query("apikey") apiKey: String, @Query("hash") hash: String, @Query("limit") limit: Int): Deferred<ResponseCharacterMarvelApi>

}