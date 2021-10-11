package com.marvellist.data.net.model

import com.google.gson.annotations.SerializedName
import com.marvellist.domain.model.ThumbnailModel

class ElementDetailApiMarvelModel(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailModel?
)

