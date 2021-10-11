package com.marvellist.domain.model

class ResponseElementByCharacterIdModel(
    val code : Int?,
    val status : String?,
    val copyRight : String?,
    val attributionText : String?,
    val attibutionHTML : String?,
    val etag : String?,
    val data: ElementsDetailModel
)