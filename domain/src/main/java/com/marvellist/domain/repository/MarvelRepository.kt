package com.marvellist.domain.repository

import com.marvellist.domain.model.RequestCharacterModel
import com.marvellist.domain.model.ResponseCharacterModel

interface MarvelRepository {
    suspend fun getCharacterById(charactersRequest: RequestCharacterModel): ResponseCharacterModel
}