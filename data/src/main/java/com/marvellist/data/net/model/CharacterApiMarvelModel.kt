package com.marvellist.data.net.model

import com.google.gson.annotations.SerializedName
import com.marvellist.domain.model.*

class CharacterApiMarvelModel(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailModel?,
    @SerializedName("resourceURI")
    val resourceURI: String?,
    @SerializedName("comics")
    val comics: ElementApiMarvelModel?,
    @SerializedName("series")
    val series: ElementApiMarvelModel?,
    @SerializedName("stories")
    val stories: ElementApiMarvelModel?,
    @SerializedName("events")
    val events: ElementApiMarvelModel?,
    @SerializedName("urls")
    val urls: List<UrlApiMarvelModel>
)