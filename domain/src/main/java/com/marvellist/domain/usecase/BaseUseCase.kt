package com.marvellist.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseUseCase <I, O> {

    suspend fun execute(input: I): O {
        return withContext(dispatcher) { useCaseFunction(input) }
    }

    protected abstract suspend fun useCaseFunction(input: I): O

    protected open val dispatcher: CoroutineDispatcher = Dispatchers.IO
}