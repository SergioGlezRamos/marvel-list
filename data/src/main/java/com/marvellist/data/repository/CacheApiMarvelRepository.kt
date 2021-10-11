package com.marvellist.data.repository

import com.marvellist.domain.model.RequestCharacterListModel
import com.marvellist.domain.model.RequestCharacterModel
import com.marvellist.domain.model.ResponseCharacterModel
import com.marvellist.domain.repository.MarvelRepository


/**
 * Repository based on Marvel API web service with cache memory implementation
 */

class CacheApiMarvelRepository(private val apiMarvelRepository: MarvelRepository) : MarvelRepository {

    override suspend fun getCharacterById(charactersRequest: RequestCharacterModel): ResponseCharacterModel {
        return apiMarvelRepository.getCharacterById(charactersRequest)
    }

    override suspend fun getCharacterList(characterListRequest: RequestCharacterListModel): ResponseCharacterModel {
        return apiMarvelRepository.getCharacterList(characterListRequest)
    }

}
