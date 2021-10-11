package com.marvellist.domain.repository

import com.marvellist.domain.model.*

interface MarvelRepository {
    suspend fun getCharacterById(charactersRequest: RequestCharacterModel): ResponseCharacterModel

    suspend fun getCharacterList(characterListRequest: RequestCharacterListModel): ResponseCharacterModel

    suspend fun getElementByCharacterId(charactersRequest: RequestElementsByCharacterIdModel): ResponseElementByCharacterIdModel
}