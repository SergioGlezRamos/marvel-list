package com.marvellist.domain.model

class DataModel(
    val offset : Int?,
    val limit : Int?,
    val total : Int?,
    val count : Int?,
    val results : List<CharacterModel>
)