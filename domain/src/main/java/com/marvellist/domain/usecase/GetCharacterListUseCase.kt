package com.marvellist.domain.usecase

import com.marvellist.domain.model.RequestCharacterListModel
import com.marvellist.domain.model.RequestCharacterModel
import com.marvellist.domain.model.ResponseCharacterModel
import com.marvellist.domain.repository.MarvelRepository


class GetCharacterListUseCase(private val marvelRepository: MarvelRepository) : BaseUseCase<RequestCharacterListModel, ResponseCharacterModel>(){

    override suspend fun useCaseFunction(input: RequestCharacterListModel): ResponseCharacterModel {
        return marvelRepository.getCharacterList(input)
    }
}