package com.marvellist.data.net.model

import com.google.gson.annotations.SerializedName

class UrlApiMarvelModel(
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
)