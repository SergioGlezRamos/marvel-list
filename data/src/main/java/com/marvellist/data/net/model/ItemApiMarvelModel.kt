package com.marvellist.data.net.model

import com.google.gson.annotations.SerializedName

class ItemApiMarvelModel(
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?
)