package com.marvellist.domain.model

class ComicModel(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<ItemModel>?
)