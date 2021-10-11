package com.marvellist.domain.usecase

import com.marvellist.domain.model.RequestElementsByCharacterIdModel
import com.marvellist.domain.model.ResponseElementByCharacterIdModel
import com.marvellist.domain.repository.MarvelRepository

class GetElementsByCharacterIdUseCase (private val marvelRepository: MarvelRepository) : BaseUseCase<RequestElementsByCharacterIdModel, ResponseElementByCharacterIdModel>() {
    override suspend fun useCaseFunction(input: RequestElementsByCharacterIdModel): ResponseElementByCharacterIdModel {
        input.id
        return marvelRepository.getElementByCharacterId(input)
    }
}