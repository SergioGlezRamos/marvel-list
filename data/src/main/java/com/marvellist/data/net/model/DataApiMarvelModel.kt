package com.marvellist.data.net.model

import com.google.gson.annotations.SerializedName

class DataApiMarvelModel(
    @SerializedName("offset")
    val offset : Int?,
    @SerializedName("limit")
    val limit : Int?,
    @SerializedName("total")
    val total : Int?,
    @SerializedName("count")
    val count : Int?,
    @SerializedName("results")
    val results : List<CharacterApiMarvelModel>
)