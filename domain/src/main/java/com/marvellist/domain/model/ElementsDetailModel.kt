package com.marvellist.domain.model

class ElementsDetailModel(
    val offset : Int?,
    val limit : Int?,
    val total : Int?,
    val count : Int?,
    val results : List<ElementModel>
)