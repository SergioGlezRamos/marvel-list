package com.marvellist.domain.model

import java.io.Serializable

class ElementModel(
    val id: Int?,
    val title: String?,
    val description: String?,
    val thumbnail: ThumbnailModel?
) : Serializable