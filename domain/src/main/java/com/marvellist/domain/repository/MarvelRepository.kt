package com.marvellist.domain.repository

import com.marvellist.domain.model.RequestCharacterListModel
import com.marvellist.domain.model.RequestCharacterModel
import com.marvellist.domain.model.ResponseCharacterModel

interface MarvelRepository {
    suspend fun getCharacterById(charactersRequest: RequestCharacterModel): ResponseCharacterModel

    suspend fun getCharacterList(characterListRequest: RequestCharacterListModel): ResponseCharacterModel
}