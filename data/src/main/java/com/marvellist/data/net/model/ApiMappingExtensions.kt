package com.marvellist.data.net.model

import com.marvellist.domain.model.RequestCharacterModel
import com.marvellist.domain.model.ResponseCharacterModel

fun RequestCharacterModel.toDataModel(): RequestCharacterMarvelApi =
    RequestCharacterMarvelApi(
        id = id
    )

fun ResponseCharacterMarvelApi.toDomainModel(): ResponseCharacterModel =
    ResponseCharacterModel(
        name = name
    )