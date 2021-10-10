package com.marvellist.domain.model

class EventModel(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<ItemModel>
)