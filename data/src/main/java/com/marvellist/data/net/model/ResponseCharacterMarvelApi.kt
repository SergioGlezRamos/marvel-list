package com.marvellist.data.net.model

import com.google.gson.annotations.SerializedName

class ResponseCharacterMarvelApi (
    @SerializedName("code")
    val code : Int?,
    @SerializedName("status")
    val status : String?,
    @SerializedName("copyRight")
    val copyRight : String?,
    @SerializedName("attributionText")
    val attributionText : String?,
    @SerializedName("attibutionHTML")
    val attibutionHTML : String?,
    @SerializedName("etag")
    val etag : String?,
    @SerializedName("data")
    val data: DataApiMarvelModel
    )