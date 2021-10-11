package com.marvellist.domain.model

class ResponseCharacterModel(
    val code : Int?,
    val status : String?,
    val copyRight : String?,
    val attributionText : String?,
    val attibutionHTML : String?,
    val etag : String?,
    val data: CharacterDetailModel
)