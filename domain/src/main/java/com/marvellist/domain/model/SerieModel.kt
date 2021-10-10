package com.marvellist.domain.model

class SerieModel(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<ItemModel>
)