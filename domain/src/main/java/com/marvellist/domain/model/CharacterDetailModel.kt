package com.marvellist.domain.model

class CharacterDetailModel(
    val offset : Int?,
    val limit : Int?,
    val total : Int?,
    val count : Int?,
    val results : List<CharacterModel>
)