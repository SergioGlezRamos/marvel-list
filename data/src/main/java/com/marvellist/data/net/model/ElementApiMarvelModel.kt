package com.marvellist.data.net.model

import com.google.gson.annotations.SerializedName

class ElementApiMarvelModel(
    @SerializedName("available")
    val available: Int?,
    @SerializedName("returned")
    val returned: Int?,
    @SerializedName("collectionURI")
    val collectionURI: String?,
    @SerializedName("items")
    val items: List<ItemApiMarvelModel>
)