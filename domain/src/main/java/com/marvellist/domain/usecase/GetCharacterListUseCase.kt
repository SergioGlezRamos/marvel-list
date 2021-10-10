package com.marvellist.domain.usecase

import com.marvellist.domain.model.ResponseCharacterModel
import com.marvellist.domain.repository.MarvelRepository

class GetCharacterListUseCase(private val marvelRepository: MarvelRepository) : BaseUseCase<Int, ResponseCharacterModel>(){

    override suspend fun useCaseFunction(input: Int): ResponseCharacterModel {
        return marvelRepository.getCharacterList(input)
    }
}