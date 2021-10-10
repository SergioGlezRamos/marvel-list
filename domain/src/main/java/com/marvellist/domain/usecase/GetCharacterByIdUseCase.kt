package com.marvellist.domain.usecase

import com.marvellist.domain.model.RequestCharacterModel
import com.marvellist.domain.model.ResponseCharacterModel
import com.marvellist.domain.repository.MarvelRepository


class GetCharacterByIdUseCase (private val marvelRepository: MarvelRepository) : BaseUseCase<RequestCharacterModel, ResponseCharacterModel>() {
    override suspend fun useCaseFunction(input: RequestCharacterModel): ResponseCharacterModel {
        input.id
        return marvelRepository.getCharacterById(input)
    }

}